package com.aryun.gmall.cart.controller;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.annotations.LoginRequired;
import com.aryun.gmall.bean.OmsCartItem;
import com.aryun.gmall.bean.PmsSkuInfo;
import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.service.PmsCartService;
import com.aryun.gmall.service.PmsSkuService;
import com.aryun.gmall.service.UserService;
import com.aryun.gmall.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
@Reference
PmsSkuService pmsSkuService;
@Reference
PmsCartService cartService;
@Reference
UserService userService;


    @RequestMapping("toTrade")
    public String toTrade(HttpServletRequest request, HttpServletResponse response, HttpSession session,ModelMap modelMap) {
        UmsMember umsMemberLogin=userService.login(new UmsMember());
        return "test";
    }

    /**
     * 进入购物车
     * @param request
     * @param response
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("cartList")
    @LoginRequired(loginSuccess = false)
    public String addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session,ModelMap modelMap) {
        String userId="1";
        List<OmsCartItem> omsCartItems=new ArrayList<>();
        //如果不是空，去查询db,redis缓存代替
        if(StringUtils.isNotBlank(userId)){
            omsCartItems=cartService.cartList(userId);
        }else{
            //去cookie里查询数据
            String cartListCookie=CookieUtil.getCookieValue(request,"cartListCookie",true);
            if(StringUtils.isNotBlank(cartListCookie)){
                //取出来赋值给OmsCartItem
              omsCartItems= JSON.parseArray(cartListCookie,OmsCartItem.class);
            }
        }
        modelMap.put("cartList",omsCartItems);
        return "cartList";
    }


    /**
     * 分支较多if为1 else为2
     * @param skuId
     * @param quantity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addToCart")
    @LoginRequired(loginSuccess = false)
    public ModelAndView addToCart(String skuId, int quantity, HttpServletRequest request, HttpServletResponse response , ModelAndView modelAndView) {
        //调用商品服务查询商品信息
        PmsSkuInfo pmsSkuInfo= pmsSkuService.getSkuById(skuId);
        //将商品信息封装购物车信息
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setCreateDate(new Date());
        omsCartItem.setDeleteStatus(0);
        omsCartItem.setModifyDate(new Date());
        omsCartItem.setPrice(pmsSkuInfo.getPrice());
        omsCartItem.setProductAttr("");
        omsCartItem.setProductBrand("三星");
        omsCartItem.setProductCategoryId(pmsSkuInfo.getCatalog3Id());
        omsCartItem.setProductId(pmsSkuInfo.getProductId());

        omsCartItem.setProductName(pmsSkuInfo.getSkuName());
        String skuName=pmsSkuInfo.getSkuName();
        omsCartItem.setProductPic(pmsSkuInfo.getSkuDefaultImg());
        //获取当前商品图片
        String skuDefaultImgUrl=pmsSkuInfo.getSkuDefaultImg();
        omsCartItem.setProductSkuCode("11111111111");
        omsCartItem.setProductSkuId(skuId);
        omsCartItem.setQuantity(quantity);
        //判断用户是否登录
        String memberId="1";
        //根据用户登录决定走cookie的分支还是db(出现大分支1用户没登录情况--1)
        if(StringUtils.isBlank(memberId)){
            //用户没有登录，将数据保存在cookie里
            List<OmsCartItem> omsCartItems=new ArrayList<>();
            //先获取cookie数据,原有的数据
            String cartListCookie =CookieUtil.getCookieValue(request,"cartListCookie",true);
            //判断cookie是否为空，是空的话可以直接赋值(出现小分支cookie为空--1--1--1)->第一if里的if判断
            if(StringUtils.isBlank(cartListCookie)){
                //直接将商品加入cookie中
                omsCartItems.add(omsCartItem);

            }else{
             omsCartItems= JSON.parseArray(cartListCookie,OmsCartItem.class);
            //判断添加的商品是否存在cookie中
            Boolean exist=ifCartExist(omsCartItems,omsCartItem);
            //如果存在就更新添加数量，cookie中不存在就添加(出现小分支--1--1--2--1)
                if(exist){
                    //将cookie中获取值的数据更新
                    for(OmsCartItem omsCartItem1:omsCartItems){
                        //更改已存在数据的信息--1--1--2--1--1
                        if(omsCartItem1.getProductSkuId().equals(omsCartItem.getProductSkuId())){
                            omsCartItem1.setQuantity(omsCartItem1.getQuantity()+omsCartItem.getQuantity());
                        }
                    }

                }
            }
            //更新Cookie 3天过期
            CookieUtil.setCookie(request,response,"cartListCookie",JSON.toJSONString(omsCartItems),60*60*72,true);

        }else{
            //用户已登录
            //从db中查出购物车数据
           OmsCartItem omsCartItemFromDb=cartService.ifCartExistByUser(memberId,skuId);

           //--1--2--1
           if(omsCartItemFromDb==null){
            //该用户没有添加过当前商品,直接添加商品
               omsCartItem.setMemberId(memberId);
               omsCartItem.setQuantity(quantity);
               cartService.addCart(omsCartItem);
           }else{
                //该用户添加过商品,更新数据，根据主键id去更新相对应的数据
               omsCartItemFromDb.setQuantity(omsCartItemFromDb.getQuantity()+omsCartItem.getQuantity());
               cartService.updateCart(omsCartItemFromDb);
           }
            //同步缓存数据
            cartService.flushCartCache(memberId);
        }
        //redirectAttributes.addFlashAttribute("skuDefaultImg",skuDefaultImgUrl);
        //redirectAttributes.addFlashAttribute("skuName",skuName);
        modelAndView.setViewName("redirect:/success.html");
        modelAndView.addObject("skuDefaultImg",skuDefaultImgUrl);
        modelAndView.addObject("skuName",skuName);
        modelAndView.addObject("skuSum",quantity);
        //重定向到下面的页面
        return modelAndView;
    }

    /**
     * 跳转页面
     * @param modelMap
     * @return
     */
    @RequestMapping("success.html")
    public String success(String skuDefaultImg ,String skuName ,String skuSum,ModelMap modelMap) {
        modelMap.put("skuName",skuName);
        modelMap.put("skuDefaultImg",skuDefaultImg);
        modelMap.put("skuSum",skuSum);
        return "success";
    }

    /**
     * 判断现有的cookie中，是否存在对应的商品数据
     * @param omsCartItems
     * @param omsCartItem
     * @return
     */
    private Boolean ifCartExist(List<OmsCartItem> omsCartItems, OmsCartItem omsCartItem) {
        boolean b = false;

        for (OmsCartItem cartItem : omsCartItems) {
            String productSkuId = cartItem.getProductSkuId();

            if (productSkuId.equals(omsCartItem.getProductSkuId())) {
                b = true;
            }
        }
        return b;
    }
    /**
     * 进入购物车
     * @param request
     * @param response
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("checkCart")
    public String checkCart(String isChecked,String skuId,int quantity,HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
        String memberId = "1";

        //判断用户是否存在，存在就调用服务，不存在就调用cookie去改变cookie数据
        // 调用服务，修改状态
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItem.setProductSkuId(skuId);
        omsCartItem.setIsChecked(isChecked);
        omsCartItem.setQuantity(quantity);
        cartService.checkCart(omsCartItem);

        // 将最新的数据从缓存中查出，渲染给内嵌页
        List<OmsCartItem> omsCartItems = cartService.cartList(memberId);
        modelMap.put("cartList", omsCartItems);
        return "cartListInner";
    }
}

package com.aryun.gmall.search.controller;

import com.aryun.gmall.annotations.LoginRequired;
import com.aryun.gmall.bean.*;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import com.aryun.gmall.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class SearchController {
    @Reference
    SearchService searchService;
    @Reference
    PmsBaseAttrInfoService pmsBaseAttrInfoService;
    @RequestMapping("index")
    @LoginRequired(loginSuccess = false)
    public String index(){

        return "index";
    }
    @RequestMapping("list.html")
    public String list(PmsSearchParam pmsSearchParam, ModelMap modelMap){

        //调用搜索服务，返回搜索结果
        List<PmsSearchSkuInfo> pmsSearchSkuInfoList=searchService.list(pmsSearchParam);
        modelMap.put("skuLsInfoList",pmsSearchSkuInfoList);
        //抽取检索结构锁，包含的平台属性集合
        Set<String> valueSet=new HashSet<>();
        for(PmsSearchSkuInfo pmsSearchSkuInfo:pmsSearchSkuInfoList){
          List<PmsSkuAttrValue> pmsSkuAttrValueList= pmsSearchSkuInfo.getSkuAttrValueList();
            pmsSkuAttrValueList.forEach((p)->{
                valueSet.add(p.getValueId());
            });
        }
        //获取属性信息和名称
       List<PmsBaseAttrInfo> pmsBaseAttrInfo= pmsBaseAttrInfoService.getAttrValueListByValueId(valueSet);
        modelMap.put("attrList",pmsBaseAttrInfo);
        //根据所选用的属性，删除当前组的属性信息
        String[]  strValId=pmsSearchParam.getValueId();
        System.out.println("======================");
        //判断是否有数据
        if(strValId!=null){
            //删除属性的同时，完成面包屑功能
            //主要属性为：属性名称，属性id,面包屑url
            List<PmsSearchCrumb> pmsSearchCrumbList=new ArrayList<>();

            Iterator<PmsBaseAttrInfo> iterator= pmsBaseAttrInfo.iterator();
            while(iterator.hasNext()){
                PmsBaseAttrInfo pmsBaseAttrInfo1= iterator.next();
                List<PmsBaseAttrValue> pmsBaseAttrValues= pmsBaseAttrInfo1.getAttrValueList();
                pmsBaseAttrValues.forEach((p)->{
                    for(int i=0;i< strValId.length;i++){
                        if(p.getId().equals(strValId[i])){
                            PmsSearchCrumb pmsSearchCrumb=new PmsSearchCrumb();
                            //面包屑：属性id
                            String valueIdCrumb=strValId[i];
                            pmsSearchCrumb.setValueId(valueIdCrumb);
                            //面包屑：获取请求的url
                            String urlParamCrumb=this.UrlParam(pmsSearchParam,strValId[i]);
                            pmsSearchCrumb.setUrlParam(urlParamCrumb);
                            //面包屑：属性名称
                            String valueNameCrumb=p.getValueName();
                            pmsSearchCrumb.setValueName(valueNameCrumb);
                            pmsSearchCrumbList.add(pmsSearchCrumb);
                            iterator.remove();
                        }
                    }
                });
            }
            //将面包屑数据导入
            modelMap.put("attrValueSelectedList",pmsSearchCrumbList);
        }
        //封装url返回前端方便属性选择添加值功能
        String urlParam=this.UrlParam(pmsSearchParam,"");
        modelMap.put("urlParam",urlParam);
        return "list";
    }

    /**
     * 封装url,用户暂时属性信息
     * @param pmsSearchParam 全局传入参数
     * @param valueId 类似构造，第二个参数只在面包屑功能生效
     * @return
     */
    private String UrlParam(PmsSearchParam pmsSearchParam,String valueId){
        String keyWord=pmsSearchParam.getKeyword();
        String catalog3Id=pmsSearchParam.getCatalog3Id();
        String[] valueIds=pmsSearchParam.getValueId();
        String strUrlParam="";

        if(StringUtils.isNotBlank(keyWord)){
            if(StringUtils.isNotBlank(strUrlParam)){
                strUrlParam=strUrlParam+"&";
            }
            strUrlParam=strUrlParam+"keyword="+keyWord;
        }

        if(StringUtils.isNotBlank(catalog3Id)){
            if(StringUtils.isNotBlank(strUrlParam)){
                strUrlParam=strUrlParam+"&";
            }
            strUrlParam=strUrlParam+"catalog3Id="+catalog3Id;
        }

        if(valueIds!=null){
            for(String str:valueIds) {
                //面包屑判断
                if(!str.equals(valueId)){
                    strUrlParam = strUrlParam + "&valueId=" + str;
                }

            }
        }

        return strUrlParam;
    }
}

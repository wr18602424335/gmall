package com.aryun.gmall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.OmsCartItem;
import com.aryun.gmall.cart.mapper.OmsCartItemMapper;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.service.PmsCartService;
import com.aryun.gmall.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service
public class PmsCartServiceImpl extends ServiceImpl<OmsCartItemMapper,OmsCartItem> implements PmsCartService {

    public static Log log = LogFactory.getLog(PmsCartServiceImpl.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    OmsCartItemMapper omsCartItemMapper;
    @Override
    public OmsCartItem ifCartExistByUser(String memberId, String skuId) {

    if(StringUtils.isNotBlank(memberId)){
        LambdaQueryWrapper<OmsCartItem> lambdaQueryWrapper= Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(OmsCartItem::getProductSkuId,skuId).eq(OmsCartItem::getMemberId,memberId);
        OmsCartItem omsCartItem=omsCartItemMapper.selectOne(lambdaQueryWrapper);
        return omsCartItem;
    } else {
        throw new MyException("1000","用户id为空");
    }
    }

    /**
     * 不存在数据----更新数据
     * @param omsCartItem
     */
    @Override
    @Transactional
    public void addCart(OmsCartItem omsCartItem) {
        if(StringUtils.isNotBlank(omsCartItem.getMemberId())){
            omsCartItemMapper.insert(omsCartItem);
        }else {
            throw new MyException("1000","用户id为空");
        }
    }

    @Override
    @Transactional
    public void flushCartCache(String memberId) {
        Map<String,Object> map= Maps.newHashMap();
        //数据库的id
        map.put("member_id",memberId);
        List<OmsCartItem> omsCartItems=omsCartItemMapper.selectByMap(map);

        //同步到redis缓存中
        Jedis jedis=redisUtil.getJedis();
        Map<String,String> omsCartItemMap= Maps.newHashMap();
        for(OmsCartItem omsCartItem:omsCartItems){
            omsCartItemMap.put(omsCartItem.getProductSkuId(), JSON.toJSONString(omsCartItem));
        }
        if(omsCartItemMap.size()==0){
            log.error("缓存存放数据为空");
        }else{
            jedis.hmset("user:"+memberId+":cart",omsCartItemMap);
        }

        //关闭
        jedis.close();
    }

    /**
     * 根据主键去更新数据
     * @param omsCartItem
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCart(OmsCartItem omsCartItem) throws MyException{
        omsCartItemMapper.updateById(omsCartItem);
    }

    /**
     * 查询缓存购物车数据
     * @param userId
     * @return
     */
    @Override
    public List<OmsCartItem> cartList(String userId) {
        Jedis jedis = null;
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        try {
            jedis = redisUtil.getJedis();

            List<String> hvals = jedis.hvals("user:" + userId + ":cart");

            for (String hval : hvals) {
                OmsCartItem omsCartItem = JSON.parseObject(hval, OmsCartItem.class);
                omsCartItems.add(omsCartItem);
            }

        }catch (Exception e){
            // 处理异常，记录系统日志
            throw new MyException("1000","系统异常");
            //String message = e.getMessage();
            //logService.addErrLog(message);
        }finally {
            jedis.close();
        }

        return omsCartItems;
    }

    //更新选中状态，更新缓存数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkCart(OmsCartItem omsCartItem) {
       LambdaQueryWrapper<OmsCartItem> lambdaQueryWrapper=Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(OmsCartItem::getMemberId,omsCartItem.getMemberId()).eq(OmsCartItem::getProductSkuId,omsCartItem.getProductSkuId());
        //更新有值的数据
        omsCartItemMapper.update(omsCartItem,lambdaQueryWrapper);
        //更新缓存
        String userId=omsCartItem.getMemberId();
        flushCartCache(userId);
    }
}

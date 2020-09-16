package com.aryun.gmall.service;

import com.aryun.gmall.bean.OmsCartItem;

import java.util.List;

public interface PmsCartService {
    //判断商品是否已经添加
    public OmsCartItem ifCartExistByUser(String memberId, String skuId);
    //添加购物车
    public void addCart(OmsCartItem omsCartItem) ;
    //刷新缓存
    public void flushCartCache(String memberId);

    void updateCart(OmsCartItem omsCartItem);
//查询缓存数据
    List<OmsCartItem> cartList(String userId);
//更改选择状态
    void checkCart(OmsCartItem omsCartItem);
}

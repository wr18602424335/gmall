package com.aryun.gmall.service;

import com.aryun.gmall.bean.PmsProductImage;
import com.aryun.gmall.bean.PmsProductInfo;
import com.aryun.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface PmsSpuService {
    /**
     * 根据id查询属性列表
     * @param spuId
     * @return
     */
    List<PmsProductInfo> spuList(String spuId);

    /**
     * 保存
     * @param pmsProductInfo
     * @return
     */
    Boolean saveSpuInfo(PmsProductInfo pmsProductInfo);

    /**
     * 根据销售品属性id查询当前属性的信息
     * @param spuId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    /**
     * 获取对应商品的图片信息
     * @param spuImageId
     * @return
     */
    List<PmsProductImage> spuImageList(String spuImageId);

    /**
     * 获取销售品属性和值的一些信息
     * @param productId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);
}

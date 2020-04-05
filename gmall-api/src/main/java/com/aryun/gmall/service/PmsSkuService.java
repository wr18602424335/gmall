package com.aryun.gmall.service;


import com.aryun.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * @author wr
 * @since
 * sku逻辑层
 */
public interface PmsSkuService {
    /**
     * 保存sku信息
     * @param pmsSkuInfo
     * @return
     */
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 获取sku商品信息
     * @param skuId
     * @return
     */
    PmsSkuInfo getSkuById(String skuId);

    /**
     * 获取sku属性值对应的spu属性值
     * @param productId
     * @return
     */
    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}

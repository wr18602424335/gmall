package com.aryun.gmall.service;

import com.aryun.gmall.bean.PmsSkuAttrValue;
import com.aryun.gmall.bean.PmsSkuImage;

import java.util.List;

public interface PmsSkuImageService {
    /**
     * 保存
     * @param pmsSkuImages
     */
    public void save(List<PmsSkuImage> pmsSkuImages);
}

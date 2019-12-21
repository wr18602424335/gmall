package com.aryun.gmall.service;

import com.aryun.gmall.bean.PmsProductImage;
import com.aryun.gmall.bean.PmsProductInfo;

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
}

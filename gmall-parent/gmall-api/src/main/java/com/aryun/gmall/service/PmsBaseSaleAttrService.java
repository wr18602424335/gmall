package com.aryun.gmall.service;

import com.aryun.gmall.bean.PmsBaseSaleAttr;
import com.aryun.gmall.bean.PmsProductInfo;

import java.util.List;

public interface PmsBaseSaleAttrService {
    /**
     * 查询所有的属性数据
     * @return
     */
    List<PmsBaseSaleAttr> attrInfoList();
}

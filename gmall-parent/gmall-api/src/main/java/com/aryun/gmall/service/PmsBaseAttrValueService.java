package com.aryun.gmall.service;


import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.bean.PmsBaseAttrValue;

import java.util.List;

/**
 *商品属性表
 */
public interface PmsBaseAttrValueService {
    /**
     * 添加平台属性值
     * @param pmsBaseAttrValues
     * @return
     */
    Boolean attrSaveValueList(List<PmsBaseAttrValue> pmsBaseAttrValues);
    /**
     * 查询属性信息根据id
     * @param
     * @return
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}

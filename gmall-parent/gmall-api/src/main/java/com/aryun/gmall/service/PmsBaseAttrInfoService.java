package com.aryun.gmall.service;


import com.aryun.gmall.bean.*;

import java.util.List;
import java.util.Set;

/**
 *商品属性表
 */
public interface PmsBaseAttrInfoService {
    /**
     * 返回3类分类的数据属性名称等
     * @param id
     * @return
     */
    List<PmsBaseAttrInfo> attrInfoList(String id);

    /**
     * 保存平台属性信息
     * @param pmsBaseAttrInfo
     * @return
     */
    String attrInfoList(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     *根据平台属性值id封装平台信息
     */
    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String>  valueSet);
}

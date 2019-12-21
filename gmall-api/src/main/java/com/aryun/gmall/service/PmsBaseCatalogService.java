package com.aryun.gmall.service;


import com.aryun.gmall.bean.PmsBaseCatalog1;
import com.aryun.gmall.bean.PmsBaseCatalog2;
import com.aryun.gmall.bean.PmsBaseCatalog3;
import com.aryun.gmall.bean.UmsMember;

import java.util.List;

/**
 *商品分类
 */
public interface PmsBaseCatalogService {
    /**
     * 返回一级查询类别
     * @return
     */
    List<PmsBaseCatalog1> getCatalog1();

    /**
     * 返回二级类别
     * @return
     */
    List<PmsBaseCatalog2> getCatalog2(String id);
    /**
     * 返回二级类别
     * @return
     */
    List<PmsBaseCatalog3> getCatalog3(String id);
}

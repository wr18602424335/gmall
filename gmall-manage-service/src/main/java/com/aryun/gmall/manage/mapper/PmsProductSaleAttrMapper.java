package com.aryun.gmall.manage.mapper;

import com.aryun.gmall.bean.PmsProductInfo;
import com.aryun.gmall.bean.PmsProductSaleAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsProductSaleAttrMapper extends BaseMapper<PmsProductSaleAttr> {

    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}

package com.aryun.gmall.manage.mapper;

import com.aryun.gmall.bean.PmsSkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsSkuInfoMapper extends BaseMapper<PmsSkuInfo> {

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}

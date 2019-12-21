package com.aryun.gmall.manage.service.impl;

import com.aryun.gmall.bean.PmsProductInfo;
import com.aryun.gmall.manage.mapper.PmsProductInfoMapper;
import com.aryun.gmall.service.PmsSpuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsSpuServiceImpl implements PmsSpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    /**
     * 根据3级id查询商品信息表
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        LambdaQueryWrapper<PmsProductInfo> lambdaQueryWrapper= Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(PmsProductInfo::getCatalog3Id,catalog3Id);
       List<PmsProductInfo> pmsProductInfoList=pmsProductInfoMapper.selectList(lambdaQueryWrapper);
        return pmsProductInfoList;
    }

    /**
     * 保存
     * @param pmsProductInfo
     * @return
     */
    @Override
    public Boolean saveSpuInfo(PmsProductInfo pmsProductInfo) {
        int i=pmsProductInfoMapper.insert(pmsProductInfo);
        System.out.println(i);
        return null;
    }
}

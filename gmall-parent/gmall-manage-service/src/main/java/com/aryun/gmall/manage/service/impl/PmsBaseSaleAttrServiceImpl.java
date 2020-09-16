package com.aryun.gmall.manage.service.impl;

import com.aryun.gmall.bean.PmsBaseSaleAttr;
import com.aryun.gmall.bean.PmsProductInfo;
import com.aryun.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.aryun.gmall.manage.mapper.PmsProductInfoMapper;
import com.aryun.gmall.service.PmsBaseSaleAttrService;
import com.aryun.gmall.service.PmsSpuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 属性表
 */
@Service
public class PmsBaseSaleAttrServiceImpl implements PmsBaseSaleAttrService {
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Override
    public List<PmsBaseSaleAttr> attrInfoList() {
        QueryWrapper<PmsBaseSaleAttr> queryWrapper=Wrappers.query();
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList=pmsBaseSaleAttrMapper.selectList(queryWrapper);
        return pmsBaseSaleAttrList;
    }
}

package com.aryun.gmall.manage.service.impl;

import com.aryun.gmall.bean.PmsBaseCatalog1;
import com.aryun.gmall.bean.PmsBaseCatalog2;
import com.aryun.gmall.bean.PmsBaseCatalog3;
import com.aryun.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.aryun.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.aryun.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.aryun.gmall.service.PmsBaseCatalogService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PmsBaseCatalogServiceImpl implements PmsBaseCatalogService {

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;
    /**
     * 调用获取一级菜单
     * @return
     */
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        List<PmsBaseCatalog1> pmsBaseCatalog1List=pmsBaseCatalog1Mapper.selectList(lambdaQueryWrapper);
        return pmsBaseCatalog1List;
    }

    /**
     * 调用获取二级菜单
     * @return
     */
    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        LambdaQueryWrapper<PmsBaseCatalog2> lambdaQueryWrapper=new LambdaQueryWrapper<PmsBaseCatalog2>();
        lambdaQueryWrapper.eq(PmsBaseCatalog2::getCatalog1Id,catalog1Id);
        List<PmsBaseCatalog2> pmsBaseCatalog2List=pmsBaseCatalog2Mapper.selectList(lambdaQueryWrapper);
        return pmsBaseCatalog2List;
    }
    /**
     * 调用获取3级菜单
     * @return
     */
    @Override
    public List<PmsBaseCatalog3> getCatalog3(String id) {
        LambdaQueryWrapper<PmsBaseCatalog3> lambdaQueryWrapper=new LambdaQueryWrapper<PmsBaseCatalog3>();
        lambdaQueryWrapper.eq(PmsBaseCatalog3::getCatalog2Id,id);
        List<PmsBaseCatalog3> pmsBaseCatalog3List=pmsBaseCatalog3Mapper.selectList(lambdaQueryWrapper);
        return pmsBaseCatalog3List;
    }
}

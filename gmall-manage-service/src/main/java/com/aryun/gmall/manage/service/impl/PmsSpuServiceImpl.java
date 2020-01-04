package com.aryun.gmall.manage.service.impl;

import com.aryun.gmall.bean.PmsProductImage;
import com.aryun.gmall.bean.PmsProductInfo;
import com.aryun.gmall.bean.PmsProductSaleAttr;
import com.aryun.gmall.bean.PmsProductSaleAttrValue;
import com.aryun.gmall.commonUtil.config.MyException;
import com.aryun.gmall.manage.mapper.PmsProductImageMapper;
import com.aryun.gmall.manage.mapper.PmsProductInfoMapper;
import com.aryun.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.aryun.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.aryun.gmall.service.PmsSpuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PmsSpuServiceImpl implements PmsSpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveSpuInfo(PmsProductInfo pmsProductInfo) {
        /**
         * 1：根据前端传入的数据，先向产品信息表中插入新增数据，
         * 2：然后获取新增id，
         * 3：向产品属性和产品图片中保存数据
         */
        int i=pmsProductInfoMapper.insert(pmsProductInfo);
        //获取产品id
        String productId=pmsProductInfo.getId();
        System.out.println("获取产品id"+productId);
        //向产品属性新增值
        List<PmsProductSaleAttr> pmsProductSaleAttrs=pmsProductInfo.getSpuSaleAttrList();
        for(PmsProductSaleAttr pmsProductSaleAttr:pmsProductSaleAttrs){
         //新增操作
            pmsProductSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);
         //添加产品属性值信息
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues=pmsProductSaleAttr.getSpuSaleAttrValueList();
            for(PmsProductSaleAttrValue pmsProductSaleAttrValue:pmsProductSaleAttrValues){
                pmsProductSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insert(pmsProductSaleAttrValue);
            }
        }
        //产品图片新增
        List<PmsProductImage> pmsProductImages=pmsProductInfo.getSpuImageList();
        for(PmsProductImage pmsProductImage:pmsProductImages){
            pmsProductImage.setProductId(productId);
            pmsProductImageMapper.insert(pmsProductImage);
        }
        return null;
    }
}

package com.aryun.gmall.item.controller;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.PmsProductSaleAttr;
import com.aryun.gmall.bean.PmsSkuInfo;
import com.aryun.gmall.bean.PmsSkuSaleAttrValue;
import com.aryun.gmall.service.PmsSkuService;
import com.aryun.gmall.service.PmsSpuService;
import com.google.common.collect.Maps;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class itemController {
    @Reference
    PmsSkuService pmsSkuService;
    @Reference
    PmsSpuService pmsSpuService;
    @RequestMapping("{skuId}.html")
    public String index3(@PathVariable String skuId,ModelMap modelMap){
        System.out.println("123开始");
        //获取销售品属性信息，主要是图片和sku的一些信息（缓存实现）
        PmsSkuInfo pmsSkuInfo=pmsSkuService.getSkuById(skuId);
        modelMap.put("skuInfo",pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs=pmsSpuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        //查询当前sku的spu的其他sku的集合的hash表
        List<PmsSkuInfo> pmsSkuInfoList=pmsSkuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        Map<StringBuilder,String> hashMap= Maps.newHashMap();
        pmsSkuInfoList.forEach(skuInfo->{
            List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues=skuInfo.getSkuSaleAttrValueList();
            //追加sale_attr_value_id
            StringBuilder k=new StringBuilder();
            String v=skuInfo.getId();
            int i=1;
            for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:pmsSkuSaleAttrValues){
                if(i!=pmsSkuSaleAttrValues.size()){
                    k.append(pmsSkuSaleAttrValue.getSaleAttrValueId()).append("|");
                }else {
                    k.append(pmsSkuSaleAttrValue.getSaleAttrValueId());
                }
                i++;
                //i=i+1;
            }
            hashMap.put(k,v);
        });
        String jsonHashMap= JSON.toJSONString(hashMap);
        modelMap.put("skuSaleAttrHashJsonStr",jsonHashMap);
        return "item";
    }
}

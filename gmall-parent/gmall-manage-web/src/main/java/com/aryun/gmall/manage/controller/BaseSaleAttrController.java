package com.aryun.gmall.manage.controller;

import com.aryun.gmall.bean.PmsBaseSaleAttr;
import com.aryun.gmall.service.PmsBaseSaleAttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BaseSaleAttrController {
    @Reference
    PmsBaseSaleAttrService pmsBaseSaleAttrService;
    /**
     * 获取属性信息表
     * @return
     */
    @ResponseBody
    @RequestMapping("baseSaleAttrList")
    public List<PmsBaseSaleAttr> attrInfoList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList=pmsBaseSaleAttrService.attrInfoList();
        System.out.println("请求来了baseSaleAttrList");
        return pmsBaseSaleAttrList;
    }
}

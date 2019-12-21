package com.aryun.gmall.manage.controller;

import com.aryun.gmall.bean.PmsBaseCatalog1;
import com.aryun.gmall.bean.PmsBaseCatalog2;
import com.aryun.gmall.bean.PmsBaseCatalog3;
import com.aryun.gmall.service.PmsBaseCatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@CrossOrigin
public class CatalogController {
    @Reference
    PmsBaseCatalogService pmsBaseCatalogService;

    @ResponseBody
    @RequestMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> pmsBaseCatalog1List=pmsBaseCatalogService.getCatalog1();
        System.out.println("请求来了");
            return pmsBaseCatalog1List;

    }
    @ResponseBody
    @RequestMapping("getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
        List<PmsBaseCatalog2> pmsBaseCatalog2List=pmsBaseCatalogService.getCatalog2(catalog1Id);
        System.out.println("请求来了getCatalog2");
        return pmsBaseCatalog2List;

    }
    @ResponseBody
    @RequestMapping("getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> pmsBaseCatalog3List=pmsBaseCatalogService.getCatalog3(catalog2Id);
        System.out.println("请求来了getCatalog3");
        return pmsBaseCatalog3List;

    }
}

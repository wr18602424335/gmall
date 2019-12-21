package com.aryun.gmall.manage.controller;

import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@CrossOrigin
public class AttrInfoController {
    @Reference
    PmsBaseAttrInfoService pmsBaseAttrInfoService;

    /**
     * 获取属性信息表
     * @return
     */
    @ResponseBody
    @RequestMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList=pmsBaseAttrInfoService.attrInfoList(catalog3Id);
        System.out.println("请求来了attrInfoList");
            return pmsBaseAttrInfoList;

    }
    /**
     * 保存
     * @return
     */
    @ResponseBody
    @RequestMapping("saveAttrInfo")
    public String attrInfoList(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
            String s=pmsBaseAttrInfoService.attrInfoList(pmsBaseAttrInfo);
            System.out.println("请求来了saveAttrInfo");
            return s;
    }
}

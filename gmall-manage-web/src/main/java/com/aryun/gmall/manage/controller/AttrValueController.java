package com.aryun.gmall.manage.controller;

import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.bean.PmsBaseAttrValue;
import com.aryun.gmall.service.PmsBaseAttrInfoService;
import com.aryun.gmall.service.PmsBaseAttrValueService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 属性值
 */
@Controller
public class AttrValueController {
    @Reference
    PmsBaseAttrValueService pmsBaseAttrValueService;
    /**
     * 获取信息请求
     * @return
     */
    @ResponseBody
    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        //查询属性信息
        List<PmsBaseAttrValue> pmsBaseAttrValueList= pmsBaseAttrValueService.getAttrValueList(attrId);
        System.out.println("请求来了getAttrValueList");
        return pmsBaseAttrValueList;
    }
}

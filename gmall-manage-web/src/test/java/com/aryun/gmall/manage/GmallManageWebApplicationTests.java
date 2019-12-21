package com.aryun.gmall.manage;

import com.aryun.gmall.bean.PmsBaseAttrInfo;
import com.aryun.gmall.manage.controller.AttrInfoController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

    @DisplayName("测试-属性信息保存")
//    @Slf4j
//    @ExtendWith(SpringExtension.class) //导入spring测试框架
    @SpringBootTest(classes = {GmallManageWebApplication.class})
//    @AutoConfigureMockMvc
//mockMvc
    class GmallManageWebApplicationTests {
        @Autowired
        AttrInfoController attrInfoController;
        @DisplayName("属性信息保存")
        @Test
        void contextLoads() {
            PmsBaseAttrInfo pmsBaseAttrInfo=new PmsBaseAttrInfo();
            pmsBaseAttrInfo.setAttrName("rui");
            pmsBaseAttrInfo.setCatalog3Id("2");
            String s="1";//attrInfoController.attrInfoList(pmsBaseAttrInfo);
            System.out.println(s);
        }

}

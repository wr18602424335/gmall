package com.aryun.gmall.user.controller;


import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello";
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        System.out.println("111");
        List<UmsMember> umsMember=userService.getAllUser();
        return umsMember;
    }
}

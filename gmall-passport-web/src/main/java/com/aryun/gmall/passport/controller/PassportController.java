package com.aryun.gmall.passport.controller;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.bean.UmsMember;
import com.aryun.gmall.ke.commons.utils.SecurityUtils;
import com.aryun.gmall.service.UserService;
import com.aryun.gmall.util.JwtUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class PassportController {
    @Reference
    UserService userService;
    /**
     * 验证用户是否存在
     * @param token
     * @return
     */
    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token, String currentIp, HttpServletRequest request){
        //调用用户模块验证用户名字和密码
        Map<String,Object> map= Maps.newHashMap();
        String ipStrMd5="";
        try {
             ipStrMd5= SecurityUtils.MD5.Bit16(currentIp);
        }catch (Exception e){
            log.error("MD5加密异常");
        }
        Map<String,Object> decode= JwtUtil.decode(token,"aryun",ipStrMd5);
        if(decode!=null){
            map.put("status","success");
            map.put("memberId",(String)decode.get("memberId"));
            map.put("nickname",(String)decode.get("nickname"));
        }else{
            map.put("status","fail");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 登录成功验证界面
     * @param umsMember
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(UmsMember umsMember ,HttpServletRequest request){
        String token="";
        //调用用户模块验证用户名字和密码
        UmsMember umsMemberLogin=userService.login(umsMember);
        if(umsMemberLogin!=null){
            //登录成功
            //用jwt制作token
            String memberId=umsMember.getId();
            String nickName=umsMember.getNickname();
            Map<String,Object> map= Maps.newHashMap();
            map.put("memberId",memberId);
            map.put("nickName",nickName);
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                if(StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }
            String ipStrMd5="";
            try {
                ipStrMd5= SecurityUtils.MD5.Bit16(ip);
            }catch (Exception e){
                log.error("MD5加密异常");
            }

            // 按照设计的算法对参数进行加密后，生成token
            token = JwtUtil.encode("2019gmall0105", map, ipStrMd5);
            // 将token存入redis一份
            userService.addUserToken(token,memberId);
        }else{
            //登录失败
            //token 为fail
            token="fail";
        }
        return token;
    }

    /**
     *登录界面验证，颁发token信息
     * @param ReturnUrl  获取请求过来的地址信息
     * @return
     */
    @RequestMapping("index")
    public String index(String ReturnUrl, ModelMap modelMap){

        modelMap.put("ReturnUrl",ReturnUrl);
        return "index";
    }
}

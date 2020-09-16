package com.aryun.gmall.interceptors;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.annotations.LoginRequired;
import com.aryun.gmall.util.CookieUtil;
import com.aryun.gmall.util.HttpclientUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 添加用户登录拦截器类
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截代码

        //根据注解结果进行拦截
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        LoginRequired loginRequired=handlerMethod.getMethodAnnotation(LoginRequired.class);
        if(loginRequired==null){
            return true;
        }
                /*需求分析(正对拦截请求方面去理解):  一下步骤的过程都是经历认证系统认证后的结果，针对这个结果去拦截
                    1.当老token是空新token也为空的的时候：从来没有登陆过
                    2.当老token是空新token不为空的的时候：刚刚登录过，需要存token
                    3.当老token不是空新token不为空的的时候：过期
                    4.当老token不是空新token为空的的时候：之前登录过了
                */
        String token="";
        //获取老的token
        String oldtoken= CookieUtil.getCookieValue(request,"oldToken",true);
        if(StringUtils.isNotBlank(oldtoken)){
            token=oldtoken;
        }

        //新token是路径上的token
        String newToken=request.getParameter("token");
        if(StringUtils.isNotBlank(newToken)){
            token=newToken;
        }
        //获取客户端ip
        String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
        if(StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr();// 从request中获取ip
            if(StringUtils.isBlank(ip)){
                ip = "127.0.0.1";
            }
        }
        //调用认证中心---http调用
        String strData=HttpclientUtil.doGet("http://192.168.0.113:8086/verify?token="+token+"&currentIp="+ip);
        //返回结果转换成map
        Map<String,Object> mapStr =JSON.parseObject(strData,Map.class);
        //登录状态
        String status=(String)mapStr.getOrDefault("status","fail");
        //用户id
        String memberId=(String)mapStr.get("memberId");
        //昵称
        String nickname=(String)mapStr.get("nickname");

        //是否必须登录
        boolean loginSuccess=loginRequired.loginSuccess();
        if(loginSuccess){
            //必须登录才行
            if(!"success".equals(status)){
                //重定向到登录页面
                StringBuffer stringBuffer=request.getRequestURL();
                response.sendRedirect("http://192.168.0.113:8086/index?ReturnUrl="+stringBuffer);
                return false;
            }
                //验证通过，覆盖token,cookie会有过期时间
                request.setAttribute("memberId",memberId);
                request.setAttribute("nickname",nickname);

        }else{
        //不需要登录时候,也可登录通过(需要校验我登录后走这种不需要登录的方法，必须也验证下)
            if("success".equals(status)){
                request.setAttribute("memberId",memberId);
                request.setAttribute("nickname",nickname);
            }
        }

        System.out.println("进入拦截器方法");
        if(StringUtils.isNotBlank(token)){
            CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
        }
        return true;
    }
}

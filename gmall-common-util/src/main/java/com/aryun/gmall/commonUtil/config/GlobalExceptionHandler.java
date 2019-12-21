package com.aryun.gmall.commonUtil.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    @ResponseBody
    public R exceptionHandle(Exception e){ // 处理方法参数的异常类型
        Map<String,Object> map=new HashMap<>();
        R r=new R();
        if(e instanceof MyException){
            map.put("code",((MyException) e).getCode());
            map.put("msg",((MyException) e).getMsg());
            r.setMap(map);
        }else{
            map.put("code",1001);
            map.put("msg","未知异常");
            r.setMap(map);
            e.printStackTrace();
        }
        return r;//自己需要实现的异常处理
    }
}

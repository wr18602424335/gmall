package com.aryun.gmall.passport.controller;

import com.alibaba.fastjson.JSON;
import com.aryun.gmall.util.HttpclientUtil;

import java.util.HashMap;
import java.util.Map;

public class Oauth2 {
    //App Key：3059068254
    //App Secret：387a98b6c9575b1a11df2965bf7964d4


    public static String getCode(){

        String s1 = HttpclientUtil.doGet("https://api.weibo.com/oauth2/authorize?client_id=3059068254&response_type=code&redirect_uri=http://192.168.0.113:8086/vlogin");

        System.out.println(s1);

        // 在第一步和第二部返回回调地址之间,有一个用户操作授权的过程

        // 2 返回授权码到回调地址

        return null;
    }

    public static String getAccess_token(){
        // 换取access_token
        // client_secret=387a98b6c9575b1a11df2965bf7964d4
        // client_id=187638711
        String s3 = "https://api.weibo.com/oauth2/access_token?";//?client_id=187638711&client_secret=a79777bba04ac70d973ee002d27ed58c&grant_type=authorization_code&redirect_uri=http://passport.gmall.com:8085/vlogin&code=CODE";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("client_id","3059068254");
        paramMap.put("client_secret","387a98b6c9575b1a11df2965bf7964d4");
        paramMap.put("grant_type","authorization_code");
        paramMap.put("redirect_uri","http://192.168.0.113:8086/vlogin");
        paramMap.put("code","bc7adb07486b00421cb18b7bc30734f9");// 授权有效期内可以使用，没新生成一次授权码，说明用户对第三方数据进行重启授权，之前的access_token和授权码全部过期
        String access_token_json = HttpclientUtil.doPost(s3, paramMap);

        Map<String,String> access_map = JSON.parseObject(access_token_json,Map.class);

        System.out.println(access_map.get("access_token"));
        System.out.println(access_map.get("uid"));

        return access_map.get("access_token");
    }

    public static Map<String,String> getUser_info(){
//2.001zFwKEY5XB2D7f552cc51fHmae1B
//3826140734
        // 4 用access_token查询用户信息
        String s4 = "https://api.weibo.com/2/users/show.json?access_token=2.001zFwKEY5XB2D7f552cc51fHmae1B&uid=3826140734";
        String user_json = HttpclientUtil.doGet(s4);
        Map<String,String> user_map = JSON.parseObject(user_json,Map.class);

        System.out.println(user_map.get("1"));

        return user_map;
    }


    public static void main(String[] args) {

        getUser_info();

    }

}

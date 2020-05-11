package com.aryun.gmall.ke.commons.utils;


import com.google.common.collect.Maps;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * @Author xuwei020
 * @Description //请求签名校验
 * @Date 14:10 2019/4/4
 * @Param
 * @return
 **/
public class CheckSignUtils {

    /**
     * api通讯签名
     *
     * @param params
     * @param secret
     * @return string
     */
    public static String getSignature(Map<String, Object> params, String secret) {
        StringBuffer strBuf = new StringBuffer();
        String serialize = "";
        Map<String, Object> sortMap = sortMapByKey(params);
        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            if (("token").equals(entry.getKey()) || ("request_id").equals(entry.getKey())
                    || ("caller").equals(entry.getKey()) || ("_url").equals(entry.getKey())) {
                continue;
            }
            if(entry.getValue() != null) {
                if (entry.getValue().getClass().equals(Boolean.class)) {
                    if ((boolean) entry.getValue()) {
                        serialize = "1";
                    } else {
                        serialize = "0";
                    }
                } else {
                    serialize = "" + entry.getValue();
                }
                strBuf.append(entry.getKey()).append("=").append(serialize).append("&");
            }
        }

        strBuf.append(secret);
        return DigestUtils.md5DigestAsHex(strBuf.toString().getBytes());
    }

    public static boolean checkSign(Object o, String secret) {
        Map<String, Object> params = object2Map(o);
        String sign = getSignature(params, secret);
        if (!sign.equals(params.get("token"))) {
            return false;
        }
        return true;
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<String, Object> sortMap = new TreeMap<String, Object>( String::compareTo);

        sortMap.putAll(map);
        return sortMap;
    }


    /**
     *      * 实体对象转成Map
     *      * @param obj 实体对象
     *      * @return
     *      
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        }catch(Exception e){
                e.printStackTrace();
            }
            return map;
        }
}


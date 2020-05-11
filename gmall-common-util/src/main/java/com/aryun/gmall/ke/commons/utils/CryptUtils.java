package com.aryun.gmall.ke.commons.utils;

//import com.ke.newhouse.activity.commons.constant.Constant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptUtils {

    private static String prefix = "newh_x_";

    public static String key;

    static {
        try {
            key = getKey("");
        } catch (Exception e) {
            log.error("Generate password Fail!");
        }
    }

    public static String getKey(String password) throws Exception {
        return SecurityUtils.MD5.Bit32(password + SecurityUtils.MD5.Bit32(password).toLowerCase() ).substring(0,16).toLowerCase();
    }

    public static String encryptToBase64(String data, String key) {
        return prefix + SecurityUtils.AES.encryptToBase64( data, key );
    }

    public static String decryptFromBase64(String data, String key) {
        String newData = data.replace(prefix,"");
        return SecurityUtils.AES.decryptFromBase64( newData, key );
    }

}

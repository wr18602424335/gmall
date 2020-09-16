//package com.aryun.gmall.ke.commons.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.MappingJsonFactory;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.text.SimpleDateFormat;
//
//public class JacksonUtils {
//    private static final Log log = LogFactory.getLog(JacksonUtils.class);
//
//    public final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
//
//    public static ObjectMapper om = (ObjectMapper) (new MappingJsonFactory().getCodec());
//
//    static {
//        om.setDateFormat(new SimpleDateFormat(dateFormat));
//        om.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
//    }
//
//    public static String obj2Json(Object obj) {
//        try {
//            return om.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            log.error(e);
//        }
//        return null;
//    }
//
//    public static <T> T json2Obj(String json, Class<T> clz) {
//        try {
//            return om.readValue(json, clz);
//        } catch (Exception e) {
//            log.error(e);
//        }
//        return null;
//    }
//
//    ;
//
//    public static <T> T json2Obj(String json, TypeReference typeReference) {
//        if (StringUtils.isEmpty(json)) {
//            return null;
//        }
//        try {
//            return om.readValue(json, typeReference);
//        } catch (Exception e) {
//            log.error(e);
//        }
//        return null;
//    }
//}

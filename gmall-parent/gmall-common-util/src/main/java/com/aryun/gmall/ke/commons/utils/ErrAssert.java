//package com.aryun.gmall.ke.commons.utils;
//
//import com.ke.newhouse.activity.commons.exception.UserVisiableException;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.Map;
//
///**
// * @author zhouqin
// * @date 2018/8/8 14:22
// */
//public class ErrAssert {
//
//    public static void err(Integer errorCode,String message) {
//        throw new UserVisiableException(errorCode,message);
//    }
//
//    public static void state(boolean expression,Integer errorCode,String message) {
//        if (!expression) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void isTrue(boolean expression,Integer errorCode, String message) {
//        if (!expression) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void isNull(Object object,Integer errorCode,String message) {
//        if (object != null) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void notNull(Object object,Integer errorCode,String message) {
//        if (object == null) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void hasLength(String text,Integer errorCode, String message) {
//        if (!StringUtils.hasLength(text)) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void hasText(String text,Integer errorCode, String message) {
//        if (!StringUtils.hasText(text)) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void notEmpty(Object[] array,Integer errorCode, String message) {
//        if (ObjectUtils.isEmpty(array)) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void noNullElements(Object[] array,Integer errorCode, String message) {
//        if (array != null) {
//            for (Object element : array) {
//                if (element == null) {
//                    throw new UserVisiableException(errorCode,message);
//                }
//            }
//        }
//    }
//
//    public static void notEmpty(Collection<?> collection,Integer errorCode, String message) {
//        if (CollectionUtils.isEmpty(collection)) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//
//    public static void notEmpty(Map<?, ?> map,Integer errorCode, String message) {
//        if (CollectionUtils.isEmpty(map)) {
//            throw new UserVisiableException(errorCode,message);
//        }
//    }
//}

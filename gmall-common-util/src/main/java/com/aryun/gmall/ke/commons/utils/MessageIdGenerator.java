//package com.aryun.gmall.ke.commons.utils;
//
////import com.ke.newhouse.activity.commons.constant.Constant;
////import com.ke.newhouse.activity.commons.enums.PrizeTypeEnum;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * @author zhiqiang zhao
// * 2019-06-20 16:16
// **/
//public class MessageIdGenerator {
//
//  public static String generateMsgId(Long activityId, String prizeType, Long prizeId, String agentId) {
//
//    return
//        Constant.MSG_PREFIX + activityId + Constant.SPLIT_STRING
//            + prizeType + Constant.SPLIT_STRING + prizeId + Constant.SPLIT_STRING
//            + StringUtils.substring(agentId, agentId.length() - 8);
//  }
//
//  public static void main(String[] args) {
//
//    String s = MessageIdGenerator.generateMsgId(1L, PrizeTypeEnum.AWARD.getTag(),2L, "1000000023103806");
//    System.out.println(s);
//  }
//}

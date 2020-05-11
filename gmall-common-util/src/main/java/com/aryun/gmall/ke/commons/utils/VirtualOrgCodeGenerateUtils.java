//package com.aryun.gmall.ke.commons.utils;
//
////import com.ke.newhouse.activity.commons.constant.Constant;
//import org.apache.commons.lang3.tuple.Pair;
//
///**
// * @author zhiqiang zhao
// * 2019-08-07 16:40
// **/
//public class VirtualOrgCodeGenerateUtils {
//
//
//  /**
//   * 生成虚拟组织code和名称
//   * code生成规则：
//   * VIRTUAL+"_"+officeAddress+"_"+positionCode+"_"+ucId
//   * codeName生成规则：
//   * VIRTUAL+"_"+officeAddress+"_"+positionCode+"_"+ucId+"_"+ucIdName
//   *
//   * @return 第一个字段返回code，第二个字段返回名称
//   */
//  public static Pair<String, String> generateVirtualCodeAndName(String officeAddress,
//      String positionCode, String ucId, String ucIdName) {
//    //
//    String orgCode = Constant.PREFIX_STRING + Constant.SPLIT_STRING +
//        officeAddress + Constant.SPLIT_STRING + positionCode + Constant.SPLIT_STRING + ucId;
//    String orgCodeName =
//        orgCode + Constant.SPLIT_STRING + ucIdName;
//    return Pair.of(orgCode, orgCodeName);
//  }
//
//
//  private VirtualOrgCodeGenerateUtils() {
//
//  }
//}

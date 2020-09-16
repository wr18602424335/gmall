package com.aryun.gmall.ke.commons.exception;

public class ExceptionConst {


  // 参数
  public static final Integer PARAM_EXCEPTION = 100;

  // 缺少参数或者参数不正确
  public static final Integer PARAM_MISSING_EXCEPTION = 101;
  // 参数验证失败
  public static final Integer PARAM_INCONFORMITY_EXCEPTION = 102;

  // 数据
  public static final Integer DATA_EXCEPTION = 200;
  // 没有数据权限失败
  public static final Integer DATA_PERMISSION_EXCEPTION = 201;
  public static final String DATA_PERMISSION_EXCEPTION_TEXT = "没有数据权限";

  // 未知异常
  public static final Integer UNKNOW_EXCEPTION = 999;
  public static final String UNKNOW_EXCEPTION_TEXT = "未知异常";
  public static final String COIN_EXCEPTION_TEXT = "查询账户余额异常";
  public static final String ACTIVITY_EXCEPTION_TEXT = "活动信息查询异常";
  public static final String PTRIZE_EXCEPTION_TEXT = "开奖信息查询异常";
  public static final String SUPPORT_EXCEPTION_TEXT = "活动入口查询异常";

  //扣减账户余额异常
  public static final Integer COIN_SUB_EXCEPTION = 300;
  public static final String COIN_SUB_EXCEPTION_TEXT = "扣减账户余额异常";
  public static final Integer COIN_CALLBACK_EXCEPTION = 301;
  public static final String COIN_CALLBACK_EXCEPTION_TEXT = "扣减账户余额异常";

  // 退款异常
  public static final Integer COIN_REFUND_EXCEPTION = 302;
  public static final String COIN_REFUND_EXCEPTION_TEXT = "退款异常";

  // 活动
  public static final Integer ACTIVITY_CAN_NOT_JOIN = 401;
  public static final String ACTIVITY_CAN_NOT_JOIN_TEXT = "无权限参加活动";
  public static final Integer ACTIVITY_NOT_EXIST = 402;
  public static final String ACTIVITY_NOT_EXIST_TEXT = "活动不存在";

  public static final Integer ACTIVITY_STATUS_EXCEPTION = 10007;
  public static final String ACTIVITY_STATUS_EXCEPTION_TEXT = "活动异常";


  //工作台workBench
  public static final String ORG_CLIENT_EXCEPTION = "701";
  public static final Integer MISSION_NOT_EXIST = 702;
  public static final String MISSION_NOT_EXIST_TEXT = "任务不存在";

  public static final Integer CONFIRM_WRONG_MISSION = 703;
  public static final String CONFIRM_WRONG_MISSION_TEXT = "不能确认和下发未处理/已确认的任务";

  public static final Integer MISSION_NOT_SAVE = 704;
  public static final String MISSION_NOT_SAVE_TEXT = "任务不能被保存，任务不存在或已下发/确认";

  public static final Integer MISSION_SAVE_NOT_EQUAL = 705;
  public static final String MISSION_SAVE_NOT_EQUAL_TEXT = "总任务量与子任务总和不等";

  public static final Integer MISSION_USER_ORGCODE_WRONG = 706;
  public static final String MISSION_USER_ORGCODE_WRONG_TEXT = "用户组织与任务组织不一致";
}

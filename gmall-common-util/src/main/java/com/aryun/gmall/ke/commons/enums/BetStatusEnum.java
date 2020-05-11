package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 投注接口／投注查询接口依赖的枚举类
 * api: /activity/bet
 * api: /activity/records
 */
public enum BetStatusEnum {
  //投注接口依赖的枚举类
  SUCCESS(1, "投注成功"),
  FAIL(0, "投注失败"),
  FAIL_WALLET(10001, "钱包余额不足"),
  FAIL_BET_MAX(10002, "投注金额超过系统允许最大值【%d】"),
  FAIL_ACCOUNT_EXCEPTION(10003, "扣除账户余额异常"),
  SAVE_BET_EXCEPTION(10004, "保存投注记录异常"),
  FAIL_NO_ACTIVITY(10005, "活动不存在"),
  NO_AUTH_ACTIVITY(10006, "暂无资格参加该活动"),
  FAIL_STATUS_WRONG(10007, "活动状态不正确"),
  FAIL_ACTIVITY_END(10008, "活动已结束");

  @Getter
  @Setter
  private Integer code;
  @Getter
  @Setter
  private String text;

  BetStatusEnum(Integer code, String text) {
    this.code = code;
    this.text = text;
  }
}

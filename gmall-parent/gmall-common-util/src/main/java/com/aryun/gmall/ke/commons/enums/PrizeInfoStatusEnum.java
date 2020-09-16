package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhiqiang zhao
 * 2019-06-19 18:45
 **/
public enum PrizeInfoStatusEnum {
  //
  WAIT_LOTTERY((byte) 0, "待派奖"),
  ALREADY_LOTTERY((byte) 1, "已派奖");

  @Getter
  @Setter
  private Byte code;
  @Getter
  @Setter
  private String text;

  PrizeInfoStatusEnum(Byte code, String text) {
    this.code = code;
    this.text = text;
  }
}

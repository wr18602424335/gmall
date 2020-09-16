package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum ShareTypeEnum {
  //
  CLICK((byte) 1, "点击"),
  BET((byte) 2, "投注");

  @Getter
  @Setter
  private Byte code;
  @Getter
  @Setter
  private String text;

  ShareTypeEnum(Byte code, String text) {
    this.code = code;
    this.text = text;
  }
}

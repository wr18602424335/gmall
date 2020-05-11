package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;

public enum ZhiwuSerielEnum {
  //
  OPERATION(0, "运营"),
  FUNCTION(1, "职能");
  @Getter
  private Integer code;
  @Getter
  private String desc;

  ZhiwuSerielEnum(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}

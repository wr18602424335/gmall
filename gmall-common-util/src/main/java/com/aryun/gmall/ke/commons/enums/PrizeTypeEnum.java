package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum PrizeTypeEnum {

  AWARD("AWARD", "赢取奖励"),
  SHARE("SHARE", "分享奖励");

  @Getter
  @Setter
  private String tag;
  @Getter
  @Setter
  private String msg;

  PrizeTypeEnum(String tag, String msg) {
    this.tag = tag;
    this.msg = msg;
  }
}

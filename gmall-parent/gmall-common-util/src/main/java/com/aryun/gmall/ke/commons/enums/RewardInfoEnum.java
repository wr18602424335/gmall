package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum RewardInfoEnum {

  BOOTH("BOOTH", "贝壳币");

  @Getter
  @Setter
  private String code;
  @Getter
  @Setter
  private String text;

  RewardInfoEnum(String code, String text) {
    this.code = code;
    this.text = text;
  }
}

package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果类型
 * Created by sun on 2018/1/4.
 **/
public enum WalletLogStatusEnum {

  SUCCESS((byte) 1, "成功"),
  FAIL((byte) 0, "失败");

  @Getter
  @Setter
  private byte code;
  @Getter
  @Setter
  private String text;

  WalletLogStatusEnum(byte code, String text) {
    this.code = code;
    this.text = text;
  }

}

package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果类型
 * Created by sun on 2018/1/4.
 **/
public enum ResultEnum {

  SUCCESS(0, "操作成功"),
  FAIL(1, "操作失败");

  @Getter
  @Setter
  private Integer code;
  @Getter
  @Setter
  private String text;

  ResultEnum(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

}

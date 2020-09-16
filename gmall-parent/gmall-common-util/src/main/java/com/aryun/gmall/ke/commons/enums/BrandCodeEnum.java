package com.aryun.gmall.ke.commons.enums;

import java.util.Arrays;
import lombok.Getter;

public enum BrandCodeEnum {
  //
  LIANJIA("lj", "链家"),
  ZHIXIAO("zx", "直销"),
  //房江湖
  FENXIAO("fx", "分销"),
  //CA
  JIAMENG("jm", "加盟");
  @Getter
  private String code;
  @Getter
  private String desc;

  BrandCodeEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  /**
   * 返回所有的品牌的code
   *
   * @return
   */
  public static String[] getAllCode() {
    return Arrays.stream(BrandCodeEnum.values())
        .map(BrandCodeEnum::getCode)
        .toArray(String[]::new);
  }

  /**
   * 返回code对应的品牌
   *
   * @return
   */
  public static BrandCodeEnum getBrand(String code) {
    for (BrandCodeEnum carrierEnum : BrandCodeEnum.values()) {
      if (carrierEnum.getCode().equals(code)) {
        return carrierEnum;
      }
    }
    return null;
  }

}

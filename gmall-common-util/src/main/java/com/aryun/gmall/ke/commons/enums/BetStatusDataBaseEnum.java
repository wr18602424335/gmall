package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据库LinkAgentBetinfo表status枚举类
 */
public enum BetStatusDataBaseEnum {
  //投注接口依赖的枚举类
  WAIT_LOTTERY((byte) 0, "待开奖"),
  ALREADY_LOTTERY((byte) 1, "已派奖"),
  BET_INVALID((byte) 2, "投注无效（扣币失败）"),
  ACTIVITY_WRONG((byte) 3, "活动异常退币"),
  REFUND_FAIL((byte) 4, "退币失败");

  @Getter
  @Setter
  private Byte code;
  @Getter
  @Setter
  private String text;

  BetStatusDataBaseEnum(Byte code, String text) {
    this.code = code;
    this.text = text;
  }


  public static BetStatusDataBaseEnum findEnumByCode(Byte code) {
    for (BetStatusDataBaseEnum carrierEnum : BetStatusDataBaseEnum.values()) {
      if (carrierEnum.getCode().equals(code)) {
        return carrierEnum;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    BetStatusDataBaseEnum enumByCode = BetStatusDataBaseEnum.findEnumByCode((byte) 4);
    System.out.println(enumByCode);
    switch (enumByCode) {
      case BET_INVALID:
        System.out.println("11111111111");
        break;
      case REFUND_FAIL:
        System.out.println("22222222222");
        break;
      case ACTIVITY_WRONG:
        System.out.println("33333333333333");
        break;
      case WAIT_LOTTERY:
        System.out.println("44444444444");
        break;
      case ALREADY_LOTTERY:
        System.out.println("55555555555555");
        break;
      default:
        System.out.println("666666666666666");
        break;
    }

    LevelCodeEnum.ORG_LEVEL_4.getDesc();
  }
}

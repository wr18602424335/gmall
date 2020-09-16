package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum WalletLogEnum {
  //经纪人钱包活动记录表 link_agent_wallet_log依赖的枚举类
  SUB_KE_COIN((byte) 0, "扣币"),
  REFUND_KE_COIN((byte) 1, "退币"),
  PRIZE_KE_COIN((byte) 2, "派发奖金"),
  KOIKE_COIN((byte) 3, "派发锦鲤奖金");

  @Getter
  @Setter
  private Byte code;
  @Getter
  @Setter
  private String text;

  WalletLogEnum(Byte code, String text) {
    this.code = code;
    this.text = text;
  }
}

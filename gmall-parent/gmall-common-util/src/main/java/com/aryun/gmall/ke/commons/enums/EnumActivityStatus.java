package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

public enum EnumActivityStatus {
  // 活动状态: 0未开始，1进行中，2结束，3开奖，4活动异常

  NOT_START((byte) 0, "未开始"),
  IN_PROGRESS((byte) 1, "进行中"),
  ENDING((byte) 2, "结束"),
  HAPPY_ENDING((byte) 3, "开奖"),
  EXCEPTION((byte) 4, "活动异常"),;

  private byte status;
  @Getter
  @Setter
  private String text;

  EnumActivityStatus(byte status, String text) {
    this.status = status;
    this.text = text;
  }

  public static EnumActivityStatus findByStatus(byte code) {
    for (EnumActivityStatus item : EnumActivityStatus.values()) {
      if (item.status == code) {
        return item;
      }
    }
    return null;
  }

//  public static void main(String[] args) {
//    EnumActivityStatus activityStatusEnum = EnumActivityStatus
//        .findByStatus(ObjectUtils.defaultIfNull((byte) 4, (byte) 3));
//    System.out.println(activityStatusEnum.text);
//
//  }

  public Byte getStatus() {
    return status;
  }

}

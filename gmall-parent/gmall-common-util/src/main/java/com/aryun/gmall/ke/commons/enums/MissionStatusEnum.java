package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum MissionStatusEnum {
  //
  CANCEL((byte) 0, "取消"),
  NOT_DEAL((byte) 1, "未处理"),
  DONE((byte) 2, "已处理"),
  SEND((byte) 3, "已下发"),
  CONFIRM((byte) 4, "确认");

  @Getter
  @Setter
  private Byte code;
  @Getter
  @Setter
  private String text;

  MissionStatusEnum(Byte code, String text) {
    this.code = code;
    this.text = text;
  }


  /**
   * 通过code查询任务状态
   *
   * @param code
   * @return
   */
  public static MissionStatusEnum findByCode(Byte code) {
    for (MissionStatusEnum item : MissionStatusEnum.values()) {
      if (item.getCode().equals(code)) {
        return item;
      }
    }
    return null;
  }
}

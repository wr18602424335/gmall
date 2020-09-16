package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author zhiqiang zhao
 * 2019-07-30 17:17
 **/
public enum UserTypeEnum {

  //
  BOSS((byte) 1, "新房总"),
  BOSS_ASSISTANT((byte) 2, "新房总助理"),
  CITY_EDITOR((byte) 3, "城市编辑"),
  UC_TYPE((byte) 4, "UC人员，指从UC处获得用户及组织架构信息"),
  CA_MANAGER((byte) 5,
      "CAD即CA总监，职位编码为15208，但是从UC处获取信息，下级人员从ods_alliance_crm_store_ca_relation_da表中获取"),
  CA((byte) 6, "CA经理，职位编码为15210/15211，从ods_alliance_crm_store_ca_relation_da表中获取"),;

  @Getter
  private byte code;
  @Getter
  private String desc;

  UserTypeEnum(byte code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static UserTypeEnum findByCode(byte code) {
    for (UserTypeEnum item : UserTypeEnum.values()) {
      if (item.code == code) {
        return item;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    UserTypeEnum userTypeEnum = UserTypeEnum
        .findByCode(ObjectUtils.defaultIfNull((byte) 4, (byte) 3));
    System.out.println(userTypeEnum);

  }

}

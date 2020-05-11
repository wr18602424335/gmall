package com.aryun.gmall.ke.commons.enums;

import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public enum LevelCodeEnum {
  //
  ORG_LEVEL_HIGH("3", "组织架构第3级", "组织架构第3级"),
  ORG_LEVEL_4("4", "组织架构第4级", "组织架构第4级"),
  ORG_LEVEL_5("5", "组织架构第5级", "组织架构第5级,也是最高级"),
  ORG_LEVEL_6("6", "组织架构第6级", "组织架构第6级"),
  ORG_LEVEL_7("7", "组织架构第7级", "组织架构第7级"),
  ORG_LEVEL_8("8", "组织架构第8级", "组织架构第8级");
  @Getter
  private String code;
  @Getter
  private String name;
  @Getter
  private String desc;

  LevelCodeEnum(String code, String name, String desc) {
    this.code = code;
    this.name = name;

    this.desc = desc;
  }


  /**
   * 返回所有的组织结构层级数组 levelCodeArray
   *
   * @return
   */
  public static Integer[] getAllLevelCode() {
    return Arrays.stream(LevelCodeEnum.values())
        .map(levelCodeEnum -> Integer.valueOf(levelCodeEnum.getCode()))
        .toArray(Integer[]::new);
  }

  /**
   * 通过code查询组织架构信息
   *
   * @param code
   * @return
   */
  public static LevelCodeEnum findByCode(String code) {
    for (LevelCodeEnum item : LevelCodeEnum.values()) {
      if (StringUtils.equals(item.code, code)) {
        return item;
      }
    }
    return null;
  }

  /**
   * 得到最高级的levelCode
   *
   * @return
   */
  public static LevelCodeEnum getHighestOrgLevel() {
    return LevelCodeEnum.ORG_LEVEL_HIGH;
  }

  public static void main(String[] args) {
    LevelCodeEnum levelCodeEnum = LevelCodeEnum
        .findByCode(ObjectUtils.defaultIfNull("5", "6"));
    System.out.println(levelCodeEnum);
    System.out.println(levelCodeEnum.getName()+"==="+levelCodeEnum.getDesc());

    Integer[] allLevelCode = LevelCodeEnum.getAllLevelCode();

    System.out.println(Arrays.toString(allLevelCode));
  }
}

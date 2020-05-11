package com.aryun.gmall.ke.commons.enums;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

public enum PositionCodeEnum {

  LIANJIA_CENTER_MAJOR("12315,1001", "链家大部总", MissionLevelEnum.LEVEL_2_LJ),
  //  FENXIAO_CENTER_MAJOR("10009,15201", "渠道总监/分销商渠道总监", BrandCodeEnum.FENXIAO),
  JIAMENG_CENTER_MAJOR("15208", "CA总监", MissionLevelEnum.LEVEL_2_JM),
  // 分销任务只编辑，不确认和保存
  FENXIAO_CENTER_MAJOR("", "新房总", MissionLevelEnum.LEVEL_2_FX),

  LIANJIA_REGION_MAJOR("768", "链家大区总", MissionLevelEnum.LEVEL_3_LJ),
  ZHIXIAO_REGION_MAJOR("10004", "直销大区总", MissionLevelEnum.LEVEL_3_ZX),
  //  FENXIAO_REGION_MAJOR("10008,15202", "渠道经理/分销商渠道经理", BrandCodeEnum.FENXIAO),
  JIAMENG_REGION_MAJOR("15210,15211", "CA经理", MissionLevelEnum.LEVEL_3_JM),

  LIANJIA_STORE_MAJOR("765", "链家圈经", MissionLevelEnum.LEVEL_4_LJ),
  ZHIXIAO_STORE_MAJOR("10003", "直销圈经", MissionLevelEnum.LEVEL_4_ZX),
//  FENXIAO_STORE_MAJOR("10006,15204", "渠道专员/分销商渠道专员", BrandCodeEnum.FENXIAO),
  ;

  @Getter
  private String code;
  @Getter
  private String desc;
  @Getter
  private MissionLevelEnum missionLevel;

  PositionCodeEnum(String code, String desc, MissionLevelEnum missionLevel) {
    this.code = code;
    this.desc = desc;
    this.missionLevel = missionLevel;
  }

  /**
   * 返回品牌和级别对应的任务
   *
   * @return
   */
  public static PositionCodeEnum getPosition(MissionLevelEnum missionLevel) {
    for (PositionCodeEnum carrierEnum : PositionCodeEnum.values()) {
      if (carrierEnum.getMissionLevel().equals(missionLevel)) {
        return carrierEnum;
      }
    }
    return null;
  }

  /**
   * 返回所有品牌对应的最高级任务
   *
   * @return
   */
  public static List<PositionCodeEnum> getAllTopPositions() {
    List<PositionCodeEnum> list = Lists.newArrayList();
    for (PositionCodeEnum carrierEnum : PositionCodeEnum.values()) {
      if (carrierEnum.getMissionLevel().equals(PositionCodeEnum.LIANJIA_CENTER_MAJOR.getMissionLevel())
              || carrierEnum.getMissionLevel().equals(PositionCodeEnum.JIAMENG_CENTER_MAJOR.getMissionLevel())
              || carrierEnum.getMissionLevel().equals(PositionCodeEnum.ZHIXIAO_REGION_MAJOR.getMissionLevel())
              || carrierEnum.getMissionLevel().equals(PositionCodeEnum.FENXIAO_CENTER_MAJOR.getMissionLevel())
              ) {
        list.add(carrierEnum);
      }
    }
    return list;
  }
}

package com.aryun.gmall.ke.commons.enums;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

public enum MissionLevelEnum {
  // 1总任务 2大部任务 3大区任务 4门店任务
  LEVEL_1((byte) 1, "总任务", null, (byte) 0),
  LEVEL_2_LJ((byte) 2, "大部任务", BrandCodeEnum.LIANJIA, (byte) 0),
  LEVEL_3_LJ((byte) 3, "大区任务", BrandCodeEnum.LIANJIA, (byte) 0),
  LEVEL_4_LJ((byte) 4, "门店任务", BrandCodeEnum.LIANJIA, (byte) 1),

  LEVEL_3_ZX((byte) 3, "大区任务", BrandCodeEnum.ZHIXIAO, (byte) 0),
  LEVEL_4_ZX((byte) 4, "门店任务", BrandCodeEnum.ZHIXIAO, (byte) 1),

  LEVEL_2_JM((byte) 2, "大区任务", BrandCodeEnum.JIAMENG, (byte) 0),
  LEVEL_3_JM((byte) 3, "门店任务", BrandCodeEnum.JIAMENG, (byte) 1),

  LEVEL_2_FX((byte) 2, "分销总任务", BrandCodeEnum.FENXIAO, (byte) 1),
  LEVEL_5((byte) 5, "品牌任务", null, (byte) 0),
  ;

  @Getter
  private Byte code;
  @Getter
  private String desc;
  @Getter
  private BrandCodeEnum brand;
  @Getter
  private Byte isLast;

  MissionLevelEnum(Byte code, String desc, BrandCodeEnum brand, Byte isLast) {
    this.code = code;
    this.desc = desc;
    this.brand = brand;
    this.isLast = isLast;
  }

  public BrandCodeEnum getBrand() {
    return brand;
  }

  /**
   * 返回品牌对应的任务
   *
   * @return
   */
  public static List<MissionLevelEnum> getMissionLevel(BrandCodeEnum brand) {
    List<MissionLevelEnum> list = Lists.newArrayList();
    for (MissionLevelEnum carrierEnum : MissionLevelEnum.values()) {
      if (carrierEnum.getBrand().equals(brand)) {
        list.add(carrierEnum);
      }
    }
    return list;
  }

  /**
   * 返回品牌和级别对应的任务
   *
   * @return
   */
  public static MissionLevelEnum getMissionLevel(String brand, Integer code) {
    BrandCodeEnum brandCodeEnum = BrandCodeEnum.getBrand(brand);
    for (MissionLevelEnum carrierEnum : MissionLevelEnum.values()) {
      if (Objects.isNull(carrierEnum.getBrand())) continue;
      if (brandCodeEnum.equals(carrierEnum.getBrand()) && carrierEnum.getCode().equals(code.byteValue())) {
        return carrierEnum;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println(MissionLevelEnum.getMissionLevel("lj",4));
  }
}



package com.aryun.gmall.ke.commons.enums;

import lombok.Getter;

public enum ConvertOrgEnum {

    // 天津4级组织code映射到5级
    ZHIXIAO("B100008","B1000081"),
    JINDONG("B100002","B000023"),
    JINXI("B100003","B000020"),
    JINNAN("B100004","B000022"),
    JINBEI("B100005","B000021"),
    ;
    @Getter
    String from;

    @Getter
    String to;

    ConvertOrgEnum(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * 返回code对应的下级编码
     *
     * @return
     */
    public static ConvertOrgEnum getNextOrg(String fromCode) {
        for (ConvertOrgEnum carrierEnum : ConvertOrgEnum.values()) {
            if (carrierEnum.getFrom().equals(fromCode)) {
                return carrierEnum;
            }
        }
        return null;
    }

}

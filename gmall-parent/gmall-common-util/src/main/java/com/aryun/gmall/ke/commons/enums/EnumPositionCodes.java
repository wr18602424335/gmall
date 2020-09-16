package com.aryun.gmall.ke.commons.enums;

import com.google.common.collect.Lists;

import java.util.List;

public enum EnumPositionCodes {

    DIRECT_SALE_A("10000", "直销经纪人A级"),
    DIRECT_ADVISOR_A("10001", "直销顾问A级"),
    DIRECT_SALE_M("10002", "直销经纪人(顾问)M级"),

    SALE_AGENT("761", "买卖经纪人"),
    RENT_AGENT("783", "租赁经纪人"),
    COM_AGENT("784", "综合经纪人"),
    LINK_AGENT("785", "联动经纪人"),
    ;

    private String positionCode;
    private String positionName;

    EnumPositionCodes(String positionCode, String positionName) {
        this.positionCode = positionCode;
        this.positionName = positionName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public static String[] getPositionCodes() {
        List<String> list = Lists.newArrayList();
        for (EnumPositionCodes codes: EnumPositionCodes.values()) {
            list.add( codes.getPositionCode() );
        }
        String[] codes = new String[list.size()];
        list.toArray(codes);
        return codes;
    }
}

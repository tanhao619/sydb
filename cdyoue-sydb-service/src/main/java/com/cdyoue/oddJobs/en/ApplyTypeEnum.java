package com.cdyoue.oddJobs.en;

/**
 * Created by dengshaojun on 2017/5/9.
 * applyType 认证类型
 */
public enum ApplyTypeEnum {

    /*认证类型：1实名，2大咖，3导师*/
    REALNAME(1, "实名"), DAKA(2, "大咖"), MENTOR(3, "导师");

    private Integer value;
    private String applyName;

    ApplyTypeEnum(Integer value, String applyName) {
        this.value = value;
        this.applyName = applyName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
}

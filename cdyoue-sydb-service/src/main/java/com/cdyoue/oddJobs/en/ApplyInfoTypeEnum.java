package com.cdyoue.oddJobs.en;

/**
 * Created by Tangguang on 2017/6/3.
 */
public enum ApplyInfoTypeEnum {
    /*认证类型：1实名，2大咖，3导师*/
    REALNAME(1,"realname", "实名"),
    DAKA(2,"daka", "大咖"),
    MENTOR(3,"mentor", "导师");
    private Integer value;
    private String applyName;
    private String applyKey;

    ApplyInfoTypeEnum(Integer value, String applyKey, String applyName) {
        this.value = value;
        this.applyName = applyName;
        this.applyKey = applyKey;
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
    public String getApplyKey() {
        return applyKey;
    }

    public void setApplyKey(final String applyKey) {
        this.applyKey = applyKey;
    }

}

package com.cdyoue.oddJobs.en;

/**
 * Created by czx on 2017/5/12.
 */
public enum UserTypeEnum {
    /*用户类型：0普通用户，1企业用户*/
    ORDINARYUSER(0, "普通用户"), ENTERPRISEUSER(1, "企业用户");

    private Integer value;
    private String name;

    UserTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

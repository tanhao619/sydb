package com.cdyoue.oddJobs.en.sydb;

/**
 * Created by dengshaojun on 2017/09/18.
 */
public enum ExpertGenderEnum {
    MAIE(new Byte("1"), "男"),
    FEMAIE(new Byte("2"), "女");

    private String genderStr;
    private Byte genderVal;

    ExpertGenderEnum(Byte genderVal, String genderStr) {
        this.genderStr = genderStr;
        this.genderVal = genderVal;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public Byte getGenderVal() {
        return genderVal;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public void setGenderVal(Byte genderVal) {
        this.genderVal = genderVal;
    }
}

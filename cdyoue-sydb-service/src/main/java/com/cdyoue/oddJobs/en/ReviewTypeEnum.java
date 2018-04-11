package com.cdyoue.oddJobs.en;

/**
 * Created by dengshaojun on 2017/6/19.
 */
public enum ReviewTypeEnum {

    PENDING(new Integer(0).byteValue(), "待审核"),
    APPROVE(new Integer(1).byteValue(), "审核通过"),
    REJECT(new Integer(2).byteValue(), "审核未通过");

    private Byte value;
    private String reviewStr;

    ReviewTypeEnum(Byte value, String reviewStr) {
        this.value = value;
        this.reviewStr = reviewStr;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public String getReviewStr() {
        return reviewStr;
    }

    public void setReviewStr(String reviewStr) {
        this.reviewStr = reviewStr;
    }

}

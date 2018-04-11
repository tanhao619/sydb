package com.cdyoue.oddJobs.en;

/**
 * Created by Administrator on 2017/5/10.
 */
public enum  AdvertisementTypeEnum {
    /* */
    sy(1,"sy"),
    cydc(2,"cydc"),
    sjfw(3,"sjfw"),
    zscx(4,"zlcx"),
    zlcxsbk(403,"zlcxsbk"),
    zlcy(5,"zlcy");

    private int id;
    private String value;

    AdvertisementTypeEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}

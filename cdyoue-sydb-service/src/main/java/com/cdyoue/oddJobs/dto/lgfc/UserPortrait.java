package com.cdyoue.oddJobs.dto.lgfc;

/**
 * Created by tr on 2017/6/1.
 */
public class UserPortrait {
    private Integer value;
    private String name;

    public UserPortrait() {
        super();
    }

    public UserPortrait(Integer value, String name) {
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

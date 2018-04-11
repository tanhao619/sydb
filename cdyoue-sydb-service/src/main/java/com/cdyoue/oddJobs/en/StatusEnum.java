package com.cdyoue.oddJobs.en;

/**
 * Created by liaoyoule on 2017/5/5.
 */
public enum StatusEnum {
    SUCCESS("success"),
    FAIL("fail");

    private String status;
    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.cdyoue.oddJobs.dto.captch;

/**
 * Created by liaoyoule on 2017/5/17.
 */
public class SmsResponse {
    private int code;
    private String msg;
    private Integer count;
    private String fee;
    private String unit;
    private String mobile;
    private String sid;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "SmsResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", fee='" + fee + '\'' +
                ", unit='" + unit + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}

package com.cdyoue.oddJobs.en;

/**
 * Created by liaoyoule on 2017/5/5.
 */
public enum  LoginTypeEnum {
    WEB("web"),
    APP("app");

    private String loginType;
    LoginTypeEnum(String client) {
        this.loginType = client;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}

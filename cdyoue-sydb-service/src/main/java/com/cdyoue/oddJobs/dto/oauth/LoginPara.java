package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m2m on 2017/3/27.
 */
public class LoginPara {
    private String clientId;
    private String userName;
    private String password;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ApiModelProperty(value = "client id",allowableValues = "098f6bcd4621d373cade4e832627b4f6,098f6bcd4621d373cade4e832627b4f65",example = "098f6bcd4621d373cade4e832627b4f65")
    public String getClientId() {
        return clientId;
    }

    @ApiModelProperty(value = "用户名 已name 匹配",example = "enter")
    public String getUserName() {
        return userName;
    }

    @ApiModelProperty(value = "密码 ",example = "123456")
    public String getPassword() {
        return password;
    }

}

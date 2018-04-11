package com.cdyoue.oddJobs.dto.account;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/6/1.
 */
public class BindSumary {

    private String captch;

    private String account;

    @ApiModelProperty(value = "验证码",example = "123456")
    public String getCaptch() {
        return captch;
    }

    public void setCaptch(String captch) {
        this.captch = captch;
    }
    @ApiModelProperty(value = "账号",example = "408648485@qq.com")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

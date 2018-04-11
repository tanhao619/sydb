package com.cdyoue.oddJobs.dto.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by liaoyoule on 2017/6/16.
 */
public class AccountResetRequest {
    @NotNull
    private Integer captcha;
    //@Pattern(regexp = "^(?=.{8,})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]*$",message = "密码由大于8位英文字母、数字、特殊字符（!@#$%^&*）组合而成，区分大小写")
    @Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$",message = "密码由大于等于8位 至少英文字母、数字两种组合而成")
    private String password;

    public Integer getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Integer captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

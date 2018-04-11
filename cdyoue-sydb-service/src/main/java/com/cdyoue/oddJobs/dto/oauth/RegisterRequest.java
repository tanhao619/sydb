package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by m2m on 2017/3/27.
 *
 */
public class RegisterRequest {
    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",message = "不合法的邮箱地址")
    private String email;
//    @Pattern(regexp ="^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,}$")
//    @Pattern(regexp = "^(?=.{8,16})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]*$",message = "密码由8-16位英文字母、数字、特殊字符（!@#$%^&*）组合而成，区分大小写")
//    @Pattern(regexp = "^(?=.{8,})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]*$",message = "密码由大于8位英文字母、数字、特殊字符（!@#$%^&*）组合而成，区分大小写")
    @Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$",message = "密码由大于等于8位 至少英文字母、数字两种组合而成")
    private String password;
    private String captcha;
    @NotNull
    private Integer userType;
    private String userName;
    @ApiModelProperty(value = "注册邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @ApiModelProperty(value = "密码")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @ApiModelProperty(value = "验证码")
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @ApiModelProperty(value = "用户类型 0：个人 1：企业")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


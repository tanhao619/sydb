package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by m2m on 2017/3/27.
 *
 */
public class RegisterByTelRequest {
//    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$",message = "请输入正确的手机号码")
    private String tel;


//    @Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$",message = "密码由大于等于8位 至少英文字母、数字两种组合而成")
    private String password;

//    @NotNull(message = "验证码不能为空")
    private Integer captcha;
    private String userName;


    @ApiModelProperty(value ="手机号码" )
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @ApiModelProperty(value ="密码" )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @ApiModelProperty(value ="验证码" )
    public Integer getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Integer captcha) {
        this.captcha = captcha;
    }


    @ApiModelProperty(value ="用户昵称" )
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}


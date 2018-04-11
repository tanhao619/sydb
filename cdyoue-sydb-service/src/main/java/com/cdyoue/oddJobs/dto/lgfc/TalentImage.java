package com.cdyoue.oddJobs.dto.lgfc;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by tr on 2017/5/10.
 * 能人画像
 */
public class TalentImage {

    //关注领域id及次数
    private FocusField focusField;
    //常用操作及次数
    private List<UserPortrait> userPortrait;
    //第一次最后一次及总登录登录
    private LoginInfoData loginInfoData;
    //登录分布
    private LoginType loginType;
    //活跃度
    private UserActiveness userActiveness;
    //登录月份统计
    private Integer[] countMonth;
    //能人性别
    private Integer sex;

    @ApiModelProperty( value = "0男；1女；2其他" )
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @ApiModelProperty( value = "常用操作")
    public List<UserPortrait> getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(List<UserPortrait> userPortrait) {
        this.userPortrait = userPortrait;
    }

    @ApiModelProperty( value = "第一次最后一次及总登录登录")
    public LoginInfoData getLoginInfoData() {
        return loginInfoData;
    }

    public void setLoginInfoData(LoginInfoData loginInfoData) {
        this.loginInfoData = loginInfoData;
    }

    @ApiModelProperty( value = "登录月份统计，从左到右1-12月")
    public Integer[] getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(Integer[] countMonth) {
        this.countMonth = countMonth;
    }

    @ApiModelProperty( value = "登录分布")
    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }


    @ApiModelProperty( value = "关注领域")
    public FocusField getFocusField() {
        return focusField;
    }

    public void setFocusField(FocusField focusField) {
        this.focusField = focusField;
    }

    @ApiModelProperty( value = "用户活跃度")
    public UserActiveness getUserActiveness() {
        return userActiveness;
    }

    public void setUserActiveness(UserActiveness userActiveness) {
        this.userActiveness = userActiveness;
    }

}

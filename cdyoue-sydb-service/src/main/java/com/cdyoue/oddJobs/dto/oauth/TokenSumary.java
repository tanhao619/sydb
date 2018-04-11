package com.cdyoue.oddJobs.dto.oauth;

import com.cdyoue.oddJobs.dto.UserMine;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/4.
 */
public class    TokenSumary implements CacheBase {
    @JsonProperty(value = "access_token")
    private String access_token;
    @JsonProperty(value = "expires_in")
    private long expires_in;
    @JsonProperty(value = "userMine")
    private UserMine userMine;
    @JsonProperty(value = "token_type")
    private String token_type;
    @JsonProperty(value = "refresh_token")
    private String refresh_token;

    @JsonProperty(value = "applyInfo")
    private Boolean applyInfo;


    public Boolean isExpires() {
        return this.expires_in - System.currentTimeMillis() < 0 ? true : false;
    }

    @ApiModelProperty(name = "是否认证（实名、企业资质）")
    public Boolean getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(final Boolean applyInfo) {
        this.applyInfo = applyInfo;
    }


    @ApiModelProperty(name = "登录凭证")
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @ApiModelProperty(name = "过期时间")
    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    @ApiModelProperty(name = "刷新凭证")
    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @ApiModelProperty(name = "token 类型")
    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @ApiModelProperty(name = "用户信息")
    public UserMine getUserMine() {
        return userMine;
    }

    public void setUserMine(UserMine userMine) {
        this.userMine = userMine;
    }

}

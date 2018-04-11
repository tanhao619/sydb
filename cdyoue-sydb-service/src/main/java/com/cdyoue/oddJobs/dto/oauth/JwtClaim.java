package com.cdyoue.oddJobs.dto.oauth;

import com.cdyoue.oddJobs.en.LoginTypeEnum;

import java.util.Date;

/**
 * Created by liaoyoule on 2017/5/5.
 */
public class JwtClaim {
    private LoginTypeEnum tokenType;
    private Integer userType;
    private String unique_name;
    private Integer role;


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LoginTypeEnum getTokenType() {
        return tokenType;
    }

    public void setTokenType(LoginTypeEnum tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUnique_name() {
        return unique_name;
    }

    public void setUnique_name(String unique_name) {
        this.unique_name = unique_name;
    }
}

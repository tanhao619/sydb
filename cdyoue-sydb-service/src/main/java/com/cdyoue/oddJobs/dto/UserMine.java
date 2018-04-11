package com.cdyoue.oddJobs.dto;

import com.cdyoue.oddJobs.dto.oauth.MenuSumary;
import com.cdyoue.oddJobs.en.LoginTypeEnum;

import java.util.List;

/**
 * Created by liaoyoule on 2017/5/18.
 */
public class UserMine {
    private Integer id;
    private Integer userType;
    private Integer role;
    private String name;
    private List<MenuSumary> topMenus;
    private LoginTypeEnum loginTypeEnum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LoginTypeEnum getLoginTypeEnum() {
        return loginTypeEnum;
    }

    public void setLoginTypeEnum(LoginTypeEnum loginTypeEnum) {
        this.loginTypeEnum = loginTypeEnum;
    }

    public List<MenuSumary> getTopMenus() {
        return topMenus;
    }

    public void setTopMenus(List<MenuSumary> topMenus) {
        this.topMenus = topMenus;
    }
}

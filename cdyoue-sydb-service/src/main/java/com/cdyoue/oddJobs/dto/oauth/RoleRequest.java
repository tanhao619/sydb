package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class RoleRequest {
    @NotNull
    @Size(min = 1)
    private String name;
    private String intro;
    private List<Integer> menus;
    @ApiModelProperty("role 名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ApiModelProperty("简介")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<Integer> getMenus() {
        return menus;
    }

    public void setMenus(List<Integer> menus) {
        this.menus = menus;
    }
}

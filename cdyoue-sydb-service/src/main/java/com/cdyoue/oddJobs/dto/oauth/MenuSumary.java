package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class MenuSumary {
    private String name;
    private String uiSref;
    private List<Menu> leftMenus;
    private String icon;
    @ApiModelProperty(value = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "连接")
    public String getUiSref() {
        return uiSref;
    }

    public void setUiSref(String uiSref) {
        this.uiSref = uiSref;
    }
    @ApiModelProperty(value = "左侧菜单")
    public List<Menu> getLeftMenus() {
        return leftMenus;
    }

    public void setLeftMenus(List<Menu> leftMenus) {
        this.leftMenus = leftMenus;
    }
    @ApiModelProperty(value = "图标")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

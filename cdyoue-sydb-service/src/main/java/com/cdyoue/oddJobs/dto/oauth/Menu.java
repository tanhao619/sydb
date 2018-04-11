package com.cdyoue.oddJobs.dto.oauth;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class Menu {
    private Integer id;
    private String name;
    private String uiSref;
    private List<Menu> menus;
    private String icon;


    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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
    @ApiModelProperty(value = "sanji")
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    @ApiModelProperty(value = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

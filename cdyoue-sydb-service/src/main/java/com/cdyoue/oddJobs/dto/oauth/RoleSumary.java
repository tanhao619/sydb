package com.cdyoue.oddJobs.dto.oauth;

import java.util.Date;
import java.util.List;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class RoleSumary {
    private Integer id;
    private String name;
    private Date createTime;
    private String creator;
    private String intro;
    private List<MenuSumary> menu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<MenuSumary> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuSumary> menu) {
        this.menu = menu;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}

package com.cdyoue.oddJobs.dto.oauth;

/**
 * Created by liaoyoule on 2017/6/12.
 */
public class RoleBase {
    private Integer id;
    private String name;
    private String intro;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}

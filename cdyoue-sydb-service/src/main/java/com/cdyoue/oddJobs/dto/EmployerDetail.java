package com.cdyoue.oddJobs.dto;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public class EmployerDetail {


    private Integer id;
    private String name;
    private String info;
    private String expertType;
    private Integer employerType;
    private String coverImgUrl;

    public Integer getEmployerType() {
        return employerType;
    }

    public void setEmployerType(Integer employerType) {
        this.employerType = employerType;
    }

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }
}

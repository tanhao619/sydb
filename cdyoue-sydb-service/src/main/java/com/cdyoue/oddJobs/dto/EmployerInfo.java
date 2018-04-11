package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public class EmployerInfo {


    private Integer id;
    private String name;
    private String info;
    private Integer employerType;
    private String coverImgUrl;

    @ApiModelProperty(value = "雇主类型")
    public Integer getEmployerType() {
        return employerType;
    }
    public void setEmployerType(Integer employerType) {
        this.employerType = employerType;
    }

    @ApiModelProperty(value = "雇主主键ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ApiModelProperty(value = "雇主姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "雇主简介")
   public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    @ApiModelProperty(value = "雇主照片")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }


}

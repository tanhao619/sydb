package com.cdyoue.oddJobs.dto.lgfc;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by admin on 2017/5/9.
 */
public class TalentCareer {
    private Integer id;
    private String name;
    private Integer userId;
    private String major;
    private String startTime;
    private String endTime;
    private String introduction;


    @ApiModelProperty(example = "", value = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "1", value = "用户id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ApiModelProperty(example = "APPLE", value = "公司名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ApiModelProperty(example = "工程师", value = "职位")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ApiModelProperty(example = "2015-05-24 18:23:21", value = "起始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @ApiModelProperty(example = "2017-02-14 22:20:21", value = "结束时间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @ApiModelProperty(example = "根据产品需求文档设计公司app及网页的原型稿，并利用Auxre做交互", value = "项目描述")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

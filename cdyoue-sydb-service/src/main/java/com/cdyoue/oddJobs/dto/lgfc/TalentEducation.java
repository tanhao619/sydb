package com.cdyoue.oddJobs.dto.lgfc;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by admin on 2017/5/8.
 */
public class TalentEducation {
    private Integer id;
    private Integer userId;
    private String name;
    private String major;
    private Byte education;
    private String startTime;
    private String endTime;

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

    @ApiModelProperty(example = "北京大学", value = "学校名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "计算机科学", value = "专业")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ApiModelProperty(example = "1", value = "学历")
    public Byte getEducation() {
        return education;
    }

    public void setEducation(Byte education) {
        this.education = education;
    }

    @ApiModelProperty(example = "2015-05-24 18:23:21", value = "起始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @ApiModelProperty(example = "2017-07-27 18:23:21", value = "结束时间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

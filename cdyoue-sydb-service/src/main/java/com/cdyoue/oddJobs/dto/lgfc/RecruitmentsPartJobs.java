package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/5/12.
 * 根据原型图设计的，方便页面显示（兼职）
 */
public class RecruitmentsPartJobs {
    @JsonProperty("id")
    private Integer id;//职位id兼职

    @JsonProperty("jobName")
    private String jobName;//职位名称

    @JsonProperty("workPlace")
    private String workPlace;//工作地点

    @JsonProperty("joinNum")
    private Integer joinNum;//应聘人数

    @JsonProperty("refreshTime")
    private String refreshTime;//刷新时间

    @JsonProperty("Salary")
    private Integer salary;//薪酬待遇

    @JsonProperty("enterpriseName")
    private String enterpriseName;//公司名称

    @JsonProperty("jobRequire")
    private String jobRequire;//兼职要求

    @JsonProperty("jobDesc")
    private String jobDesc;//职位描述

    /*@JsonProperty("startTime")
    private String startTime;//开始时间

    @JsonProperty("endTime")
    private String endTime;//结束时间*/

    @JsonProperty("workTime")
    private String workTime;//工作时间

    public void setId(Integer id) {
        this.id = id;
    }
    @ApiModelProperty(example = "兼职id：123", value = "123")
    public Integer getId() {
        return id;
    }

    @ApiModelProperty(example = "职位描述：该兼职主要做xxxxxxx", value = "职位描述")
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @ApiModelProperty(example = "职位名称：java工程师", value = "职位名称")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @ApiModelProperty(example = "工作地点：北京", value = "工作地点")
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @ApiModelProperty(example = "应聘人数(获得企业发布的兼职接口会用到)：20", value = "应聘人数")
    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    @ApiModelProperty(example = "刷新时间：2017/03/17", value = "刷新时间")
    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    @ApiModelProperty(example = "薪酬：20K", value = "薪酬")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @ApiModelProperty(example = "公司名称(获得个人投递兼职接口会用到)：优易数据", value = "公司名称")
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    @ApiModelProperty(example = "职位要求(获得个人投递兼职接口会用到)：20岁以上", value = "职位要求")
    public String getJobRequire() {
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }
    /*@ApiModelProperty(example = "开始时间：2017/03/03 09:30", value = "开始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @ApiModelProperty(example = "结束时间：2017/04/03 18:00", value = "结束时间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }*/

    @ApiModelProperty(example = "工作时间：2017/04/03 18:00-23:00", value = "工作时间")
    public String getWorkTime() {
        return workTime;
    }
    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}

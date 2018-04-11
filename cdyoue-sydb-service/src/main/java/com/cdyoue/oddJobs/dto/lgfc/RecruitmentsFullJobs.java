package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/5/12.
 * 根据原型图设计的，方便页面显示（全职）
 */
public class RecruitmentsFullJobs {
    @JsonProperty("id")
    private Integer id;//职位id全职

    @JsonProperty("jobName")
    private String jobName;//职位名称

    @JsonProperty("workPlace")
    private String workPlace;//工作地点

    @JsonProperty("workingLife")
    private Integer workingLife;//工作经验

    @JsonProperty("refreshTime")
    private String refreshTime;//刷新时间

    @JsonProperty("Salary")
    private Integer Salary;//薪酬待遇

    @JsonProperty("enterpriseName")
    private String enterpriseName;//公司名称

    @JsonProperty("enterpriseLogo")
    private String enterpriseLogo;//公司LOGO

    @JsonProperty("welfare")
    private String welfare;//公司福利

    @JsonProperty("joinNum")
    private Integer joinNum;//应聘人数

    public void setId(Integer id) {
        this.id = id;
    }
    @ApiModelProperty(example = "全职id：123", value = "123")
    public Integer getId() {
        return id;
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
    @ApiModelProperty(example = "工作经验：3年", value = "工作经验")
    public Integer getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(Integer workingLife) {
        this.workingLife = workingLife;
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
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }
    @ApiModelProperty(example = "公司名称(获得个人投递全职接口会用到)：优易数据", value = "公司名称")
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    @ApiModelProperty(example = "公司logo(获得个人投递全职接口会用到)：xxx/xxx/xxx.jpg", value = "公司logo")
    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }
    @ApiModelProperty(example = "公司福利：五险一金", value = "公司福利")
    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }
    @ApiModelProperty(example = "应聘人数(获得企业发布的全职接口会用到)：20", value = "应聘人数")
    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }
}

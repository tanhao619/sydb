package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * RecruitmentsInfo
 * 作为发布兼职/全职的表单（公共部分）
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsInfo {

    @JsonProperty("expectFunctionCategory")
    private Integer expectFunctionCategory = null;

    @JsonProperty("jobName")
    private String jobName = null;

    @JsonProperty("jobDesc")
    private String jobDesc = null;

    @JsonProperty("JobRequire")
    private String jobRequire = null;

    @JsonProperty("contact")
    private String contact = null;

    @JsonProperty("tel")
    private String tel = null;

    @JsonProperty("workPlace")
    private String workPlace = null;


    /**
     * 职能类别、工作分类(关联职能表)
     * @return expectFunctionCategory
     **/
    @ApiModelProperty(example = "1", value = "职能类别、工作分类")
    public Integer getExpectFunctionCategory() {
        return expectFunctionCategory;
    }

    public void setExpectFunctionCategory(Integer expectFunctionCategory) {
        this.expectFunctionCategory = expectFunctionCategory;
    }

    @ApiModelProperty(example = "打劫", value = "职位名字")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @ApiModelProperty(example = "三年血赚，死刑不亏", value = "职位描述")
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @ApiModelProperty(example = "是个人就行了", value = "职位要求")
    public String getJobRequire() {
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    @ApiModelProperty(example = "盖伦", value = "联系人")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @ApiModelProperty(example = "4008208895", value = "电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    @ApiModelProperty(example = "成都市郫都区德源镇静园东路138号", value = "工作地点")
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

}


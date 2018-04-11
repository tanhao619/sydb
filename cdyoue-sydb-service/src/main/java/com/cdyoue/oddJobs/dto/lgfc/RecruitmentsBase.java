package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * RecruitmentsBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsBase   {
  @JsonProperty("id")
  private Integer id = null;

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

  @JsonProperty("educationalBackground")
  private Integer educationalBackground = null;

  @JsonProperty("workingLife")
  private Integer workingLife = null;

  @JsonProperty("joinNum")
  private Integer joinNum=null;//应聘人数

  @JsonProperty("workPlace")
  private String workPlace = null;

  @JsonProperty("expectFunctionCategory")
  private Integer expectFunctionCategory = null;

  @JsonProperty("categoryName")
  private String categoryName = null;

  public RecruitmentsBase() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ApiModelProperty(example = "职位名字",value = "职位名字")
  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  @ApiModelProperty(example = "职位描述",value = "职位描述")
  public String getJobDesc() {
    return jobDesc;
  }

  public void setJobDesc(String jobDesc) {
    this.jobDesc = jobDesc;
  }

  @ApiModelProperty(example = "职位要求",value = "职位要求")
  public String getJobRequire() {
    return jobRequire;
  }

  public void setJobRequire(String jobRequire) {
    this.jobRequire = jobRequire;
  }

  @ApiModelProperty(example = "联系人",value = "联系人")
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @ApiModelProperty(example = "电话",value = "电话")
  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  /**
   * 学历
   * @return salary
   **/
  @ApiModelProperty(example = "学历（1：高中/中专；2：大专；3：本科；4：硕士；5：博士；6：博士后）", value = "学历")
  public Integer getEducationalBackground() {
    return educationalBackground;
  }

  public void setEducationalBackground(Integer educationalBackground) {
    this.educationalBackground = educationalBackground;
  }

  /**
   * 工作年限
   * @return workPlace
   **/
  @ApiModelProperty(example = "工作年限:1、0-1,2、1-3,3、3-5,4、5-10,5、10以上",value = "工作年限")
  public Integer getWorkingLife() {
    return workingLife;
  }

  public void setWorkingLife(Integer workingLife) {
    this.workingLife = workingLife;
  }

  @ApiModelProperty(example = "工作地点",value = "工作地点")
  public String getWorkPlace() {
    return workPlace;
  }

  public void setWorkPlace(String workPlace) {
    this.workPlace = workPlace;
  }

  /**
   * 职能类别
   * @return expectFunctionCategory
   **/
  @ApiModelProperty(example = "职能类别、工作分类",value = "职能类别")
  public Integer getExpectFunctionCategory() {
    return expectFunctionCategory;
  }

  public void setExpectFunctionCategory(Integer expectFunctionCategory) {
    this.expectFunctionCategory = expectFunctionCategory;
  }
  /**
   * 职能类别的具体名字
   * @return categoryName
   **/
  @ApiModelProperty(example = "职能类别、工作分类",value = "职能类别的具体名字")
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Integer getJoinNum() {
    return joinNum;
  }
  @ApiModelProperty(value = "应聘人数")
  public void setJoinNum(Integer joinNum) {
    this.joinNum = joinNum;
  }
}


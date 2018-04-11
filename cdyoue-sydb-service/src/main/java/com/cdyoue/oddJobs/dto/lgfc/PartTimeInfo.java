package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * PartTimeInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class PartTimeInfo   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("EnterpriseName")
  private String enterpriseName = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("workPlace")
  private String workPlace = null;

  @JsonProperty("salary")
  private String salary = null;

  @JsonProperty("welfare")
  private String welfare = null;

  @JsonProperty("jobDesc")
  private String jobDesc = null;

  @JsonProperty("workTime")
  private String workTime = null;

  public PartTimeInfo id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PartTimeInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 职位名称
   * @return name
  **/
  @ApiModelProperty(example = "知识产权专员", value = "职位名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PartTimeInfo enterpriseName(String enterpriseName) {
    this.enterpriseName = enterpriseName;
    return this;
  }

   /**
   * 公司名称
   * @return enterpriseName
  **/
  @ApiModelProperty(example = "国信优易数据有限公司", value = "公司名称")
  public String getEnterpriseName() {
    return enterpriseName;
  }

  public void setEnterpriseName(String enterpriseName) {
    this.enterpriseName = enterpriseName;
  }

  public PartTimeInfo createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * 创建时间
   * @return createTime
  **/
  @ApiModelProperty(example = "2015-1-14", value = "创建时间")
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public PartTimeInfo workPlace(String workPlace) {
    this.workPlace = workPlace;
    return this;
  }

   /**
   * 工作地址
   * @return workPlace
  **/
  @ApiModelProperty(example = "四川-成都", value = "工作地址")
  public String getWorkPlace() {
    return workPlace;
  }

  public void setWorkPlace(String workPlace) {
    this.workPlace = workPlace;
  }

  public PartTimeInfo salary(String salary) {
    this.salary = salary;
    return this;
  }

   /**
   * 薪资
   * @return salary
  **/
  @ApiModelProperty(example = "50元/小时", value = "薪资")
  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public PartTimeInfo welfare(String welfare) {
    this.welfare = welfare;
    return this;
  }

   /**
   * 公司福利
   * @return welfare
  **/
  @ApiModelProperty(example = "五险一金", value = "公司福利")
  public String getWelfare() {
    return welfare;
  }

  public void setWelfare(String welfare) {
    this.welfare = welfare;
  }

  public PartTimeInfo jobDesc(String jobDesc) {
    this.jobDesc = jobDesc;
    return this;
  }

   /**
   * 职位描述
   * @return jobDesc
  **/
  @ApiModelProperty(example = "要求能吃苦", value = "职位描述")
  public String getJobDesc() {
    return jobDesc;
  }

  public void setJobDesc(String jobDesc) {
    this.jobDesc = jobDesc;
  }

  public PartTimeInfo workTime(String workTime) {
    this.workTime = workTime;
    return this;
  }

   /**
   * 工作时间
   * @return workTime
  **/
  @ApiModelProperty(example = "2017-4-15 9:00至2017-4-15 17:30", value = "工作时间")
  public String getWorkTime() {
    return workTime;
  }

  public void setWorkTime(String workTime) {
    this.workTime = workTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartTimeInfo partTimeInfo = (PartTimeInfo) o;
    return Objects.equals(this.id, partTimeInfo.id) &&
        Objects.equals(this.name, partTimeInfo.name) &&
        Objects.equals(this.enterpriseName, partTimeInfo.enterpriseName) &&
        Objects.equals(this.createTime, partTimeInfo.createTime) &&
        Objects.equals(this.workPlace, partTimeInfo.workPlace) &&
        Objects.equals(this.salary, partTimeInfo.salary) &&
        Objects.equals(this.welfare, partTimeInfo.welfare) &&
        Objects.equals(this.jobDesc, partTimeInfo.jobDesc) &&
        Objects.equals(this.workTime, partTimeInfo.workTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, enterpriseName, createTime, workPlace, salary, welfare, jobDesc, workTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartTimeInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    enterpriseName: ").append(toIndentedString(enterpriseName)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    workPlace: ").append(toIndentedString(workPlace)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    welfare: ").append(toIndentedString(welfare)).append("\n");
    sb.append("    jobDesc: ").append(toIndentedString(jobDesc)).append("\n");
    sb.append("    workTime: ").append(toIndentedString(workTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


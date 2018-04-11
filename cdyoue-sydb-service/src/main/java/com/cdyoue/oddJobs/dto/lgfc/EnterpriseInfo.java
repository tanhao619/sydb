package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * EnterpriseInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class EnterpriseInfo   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("workPlace")
  private String workPlace = null;

  @JsonProperty("salary")
  private String salary = null;

  @JsonProperty("welfare")
  private String welfare = null;

  @JsonProperty("workingLife")
  private String workingLife = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  public EnterpriseInfo id(Integer id) {
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

  public EnterpriseInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 公司名称
   * @return name
  **/
  @ApiModelProperty(example = "国信优易数据有限公司", value = "公司名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EnterpriseInfo createTime(String createTime) {
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

  public EnterpriseInfo workPlace(String workPlace) {
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

  public EnterpriseInfo salary(String salary) {
    this.salary = salary;
    return this;
  }

   /**
   * 薪资
   * @return salary
  **/
  @ApiModelProperty(example = "7k-10k", value = "薪资")
  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public EnterpriseInfo welfare(String welfare) {
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

  public EnterpriseInfo workingLife(String workingLife) {
    this.workingLife = workingLife;
    return this;
  }

   /**
   * 工作年限
   * @return workingLife
  **/
  @ApiModelProperty(example = "3-5", value = "工作年限")
  public String getWorkingLife() {
    return workingLife;
  }

  public void setWorkingLife(String workingLife) {
    this.workingLife = workingLife;
  }

  public EnterpriseInfo coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * 封面
   * @return coverImgUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseInfo enterpriseInfo = (EnterpriseInfo) o;
    return Objects.equals(this.id, enterpriseInfo.id) &&
        Objects.equals(this.name, enterpriseInfo.name) &&
        Objects.equals(this.createTime, enterpriseInfo.createTime) &&
        Objects.equals(this.workPlace, enterpriseInfo.workPlace) &&
        Objects.equals(this.salary, enterpriseInfo.salary) &&
        Objects.equals(this.welfare, enterpriseInfo.welfare) &&
        Objects.equals(this.workingLife, enterpriseInfo.workingLife) &&
        Objects.equals(this.coverImgUrl, enterpriseInfo.coverImgUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createTime, workPlace, salary, welfare, workingLife, coverImgUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnterpriseInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    workPlace: ").append(toIndentedString(workPlace)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    welfare: ").append(toIndentedString(welfare)).append("\n");
    sb.append("    workingLife: ").append(toIndentedString(workingLife)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
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


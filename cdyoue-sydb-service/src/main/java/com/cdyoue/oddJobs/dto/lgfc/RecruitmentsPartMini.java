package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Date;
import java.util.Objects;

import com.cdyoue.oddJobs.dto.account.EnterAccountDetail;
import com.cdyoue.oddJobs.dto.account.EnterAccountForRecruitment;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * RecruitmentsPartMini
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsPartMini   {
  @JsonProperty("RecruitmentsBase")
  private RecruitmentsBase recruitmentsBase = null;

  @JsonProperty("enterAccountForRecruitment")
  private EnterAccountForRecruitment enterAccountForRecruitment = null;

  @JsonProperty("welfare")
  private String welfare = null;

  @JsonProperty("refreshTime")
  private String refreshTime = null;

  @JsonProperty("salary")
  private String salary = null;

  @JsonProperty("starTime")
  private String starTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("enterpriseInfo")
  private String enterpriseInfo = null;

  @JsonProperty("workTime")
  private  String workTime = null;

  @JsonProperty("applyFor")
  private boolean applyFor;

  @JsonProperty("createTime")

  private Date createTime;

  public RecruitmentsPartMini recruitmentsBase(RecruitmentsBase recruitmentsBase) {
    this.recruitmentsBase = recruitmentsBase;
    return this;
  }

   /**
   * Get recruitmentsBase
   * @return recruitmentsBase
  **/
  @ApiModelProperty(value = "")
  public RecruitmentsBase getRecruitmentsBase() {
    return recruitmentsBase;
  }

  public void setRecruitmentsBase(RecruitmentsBase recruitmentsBase) {
    this.recruitmentsBase = recruitmentsBase;
  }

  public RecruitmentsPartMini welfare(String welfare) {
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

  public RecruitmentsPartMini refreshTime(String refreshTime) {
    this.refreshTime = refreshTime;
    return this;
  }

   /**
   * 刷新时间
   * @return refreshTime
  **/
  @ApiModelProperty(example = "2017-2-3", value = "刷新时间")
  public String getRefreshTime() {
    return refreshTime;
  }

  public void setRefreshTime(String refreshTime) {
    this.refreshTime = refreshTime;
  }

  public RecruitmentsPartMini salary(String salary) {
    this.salary = salary;
    return this;
  }

   /**
   * 薪资待遇
   * @return salary
  **/
  @ApiModelProperty(example = "20元/小时", value = "薪资待遇")
  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public RecruitmentsPartMini starTime(String starTime) {
    this.starTime = starTime;
    return this;
  }

   /**
   * 开始时间
   * @return starTime
  **/
  @ApiModelProperty(example = "2017-01-01T00:00:00.000Z", value = "开始时间")
  public String getStarTime() {
    return starTime;
  }

  public void setStarTime(String starTime) {
    this.starTime = starTime;
  }

  public RecruitmentsPartMini endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * 结束时间
   * @return endTime
  **/
  @ApiModelProperty(example = "2017-01-02T00:00:00.000Z", value = "结束时间")
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public RecruitmentsPartMini enterpriseInfo(String enterpriseInfo) {
    this.enterpriseInfo = enterpriseInfo;
    return this;
  }

   /**
   * 公司介绍
   * @return enterpriseInfo
  **/
  @ApiModelProperty(example = "此公司不得了", value = "公司介绍")
  public String getEnterpriseInfo() {
    return enterpriseInfo;
  }

  public void setEnterpriseInfo(String enterpriseInfo) {
    this.enterpriseInfo = enterpriseInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecruitmentsPartMini recruitmentsPartMini = (RecruitmentsPartMini) o;
    return Objects.equals(this.recruitmentsBase, recruitmentsPartMini.recruitmentsBase) &&
        Objects.equals(this.welfare, recruitmentsPartMini.welfare) &&
        Objects.equals(this.refreshTime, recruitmentsPartMini.refreshTime) &&
        Objects.equals(this.salary, recruitmentsPartMini.salary) &&
        Objects.equals(this.starTime, recruitmentsPartMini.starTime) &&
        Objects.equals(this.endTime, recruitmentsPartMini.endTime) &&
        Objects.equals(this.enterpriseInfo, recruitmentsPartMini.enterpriseInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recruitmentsBase, welfare, refreshTime, salary, starTime, endTime, enterpriseInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecruitmentsPartMini {\n");
    
    sb.append("    recruitmentsBase: ").append(toIndentedString(recruitmentsBase)).append("\n");
    sb.append("    welfare: ").append(toIndentedString(welfare)).append("\n");
    sb.append("    refreshTime: ").append(toIndentedString(refreshTime)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    starTime: ").append(toIndentedString(starTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    enterpriseInfo: ").append(toIndentedString(enterpriseInfo)).append("\n");
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

  public EnterAccountForRecruitment getEnterAccountForRecruitment() {
    return enterAccountForRecruitment;
  }

  public void setEnterAccountForRecruitment(EnterAccountForRecruitment enterAccountForRecruitment) {
    this.enterAccountForRecruitment = enterAccountForRecruitment;
  }

  /**
   * 是否已应聘
   * @return
   */
  @ApiModelProperty(example = "是否已应聘", value = "是否已应聘")

  public boolean isApplyFor() {
    return applyFor;
  }

  public void setApplyFor(boolean applyFor) {
    this.applyFor = applyFor;
  }

  @ApiModelProperty(example = "周末工作", value = "工作时间")
  public String getWorkTime() {
    return workTime;
  }

  public void setWorkTime(String workTime) {
    this.workTime = workTime;
  }
  @ApiModelProperty(value = "创建时间")

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}


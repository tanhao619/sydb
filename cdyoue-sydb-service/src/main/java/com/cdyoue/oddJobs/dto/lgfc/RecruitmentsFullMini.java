package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Date;
import java.util.Objects;

import com.cdyoue.oddJobs.dto.account.EnterAccountDetail;
import com.cdyoue.oddJobs.dto.account.EnterAccountForRecruitment;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * RecruitmentsFullMini
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsFullMini   {
  @JsonProperty("RecruitmentsBase")
  private RecruitmentsBase recruitmentsBase = null;

  @JsonProperty("enterAccountForRecruitment")
  private EnterAccountForRecruitment enterAccountForRecruitment = null;

  @JsonProperty("enterAccountDetail")
  private EnterAccountDetail enterAccountDetail = null;

  @JsonProperty("welfare")
  private String welfare = null;

  @JsonProperty("refreshTime")
  private String refreshTime = null;

  @JsonProperty("expectedSalary")
  private Integer expectedSalary = null;

  @JsonProperty("applyFor")
  private boolean applyFor;


  @JsonProperty("createTime")
  private Date createTime;


  public RecruitmentsFullMini() {
  }

  public RecruitmentsFullMini recruitmentsBase(RecruitmentsBase recruitmentsBase) {
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

  public RecruitmentsFullMini welfare(String welfare) {
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

  public RecruitmentsFullMini refreshTime(String refreshTime) {
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

  public RecruitmentsFullMini expectedSalary(Integer expectedSalary) {
    this.expectedSalary = expectedSalary;
    return this;
  }

   /**
   * 薪资待遇
   * @return expectedSalary
  **/
  @ApiModelProperty(example = "期望薪资:1、3000以下，2、3000-5000，3、5000-10000，4、10000-20000，5、20000以上", value = "薪资待遇")
  public Integer getExpectedSalary() {
    return expectedSalary;
  }

  public void setExpectedSalary(Integer expectedSalary) {
    this.expectedSalary = expectedSalary;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecruitmentsFullMini recruitmentsFullMini = (RecruitmentsFullMini) o;
    return Objects.equals(this.recruitmentsBase, recruitmentsFullMini.recruitmentsBase) &&
        Objects.equals(this.welfare, recruitmentsFullMini.welfare) &&
        Objects.equals(this.refreshTime, recruitmentsFullMini.refreshTime) &&
        Objects.equals(this.expectedSalary, recruitmentsFullMini.expectedSalary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recruitmentsBase, welfare, refreshTime, expectedSalary);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecruitmentsFullMini {\n");
    
    sb.append("    recruitmentsBase: ").append(toIndentedString(recruitmentsBase)).append("\n");
    sb.append("    welfare: ").append(toIndentedString(welfare)).append("\n");
    sb.append("    refreshTime: ").append(toIndentedString(refreshTime)).append("\n");
    sb.append("    expectedSalary: ").append(toIndentedString(expectedSalary)).append("\n");
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

  public EnterAccountDetail getEnterAccountDetail() {
    return enterAccountDetail;
  }

  public void setEnterAccountDetail(EnterAccountDetail enterAccountDetail) {
    this.enterAccountDetail = enterAccountDetail;
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
  @ApiModelProperty(value = "创建时间")

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}


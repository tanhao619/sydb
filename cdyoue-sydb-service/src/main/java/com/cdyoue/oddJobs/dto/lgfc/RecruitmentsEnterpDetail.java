package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * RecruitmentsEnterpDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsEnterpDetail   {
  @JsonProperty("RecruitmentsFullMini")
  private RecruitmentsFullMini recruitmentsFullMini = null;

  @JsonProperty("enterprise")
  private String enterprise = null;

  @JsonProperty("joinNum")
  private Integer joinNum = null;

  public RecruitmentsEnterpDetail() {
  }

  public RecruitmentsEnterpDetail recruitmentsFullMini(RecruitmentsFullMini recruitmentsFullMini) {
    this.recruitmentsFullMini = recruitmentsFullMini;
    return this;
  }

  public RecruitmentsFullMini getRecruitmentsFullMini() {
    return recruitmentsFullMini;
  }

  public void setRecruitmentsFullMini(RecruitmentsFullMini recruitmentsFullMini) {
    this.recruitmentsFullMini = recruitmentsFullMini;
  }

  /**
   * Get recruitmentsFullMini
   * @return recruitmentsFullMini
  **/
  @ApiModelProperty(value = "")


  public RecruitmentsEnterpDetail enterprise(String enterprise) {
    this.enterprise = enterprise;
    return this;
  }

   /**
   * 公司名称
   * @return enterprise
  **/
  @ApiModelProperty(example = "优易数据", value = "公司名称")
  public String getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(String enterprise) {
    this.enterprise = enterprise;
  }

  public RecruitmentsEnterpDetail joinNum(Integer joinNum) {
    this.joinNum = joinNum;
    return this;
  }

   /**
   * 报名人数
   * @return joinNum
  **/
  @ApiModelProperty(example = "20", value = "报名人数")
  public Integer getJoinNum() {
    return joinNum;
  }

  public void setJoinNum(Integer joinNum) {
    this.joinNum = joinNum;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecruitmentsEnterpDetail recruitmentsEnterpDetail = (RecruitmentsEnterpDetail) o;
    return Objects.equals(this.recruitmentsFullMini, recruitmentsEnterpDetail.recruitmentsFullMini) &&
        Objects.equals(this.enterprise, recruitmentsEnterpDetail.enterprise) &&
        Objects.equals(this.joinNum, recruitmentsEnterpDetail.joinNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recruitmentsFullMini, enterprise, joinNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecruitmentsEnterpDetail {\n");
    
    sb.append("    recruitmentsFullMini: ").append(toIndentedString(recruitmentsFullMini)).append("\n");
    sb.append("    enterprise: ").append(toIndentedString(enterprise)).append("\n");
    sb.append("    joinNum: ").append(toIndentedString(joinNum)).append("\n");
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


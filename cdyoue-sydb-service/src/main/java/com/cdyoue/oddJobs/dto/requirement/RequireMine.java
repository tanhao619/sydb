package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.common.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * RequireMine
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireMine   {
  @JsonProperty("requireBase")
  private RequireBase requireBase = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  private String info;

  private String employerName;

  private Category category;

  private Integer acceptNum;

  @JsonProperty("approveStatus")
  private Integer approveStatus = null;

  public RequireMine requireBase(RequireBase requireBase) {
    this.requireBase = requireBase;
    return this;
  }

   /**
   * Get requireBase
   * @return requireBase
  **/
  @ApiModelProperty(value = "")
  public RequireBase getRequireBase() {
    return requireBase;
  }

  public void setRequireBase(RequireBase requireBase) {
    this.requireBase = requireBase;
  }

  public RequireMine coverImgUrl(String coverImgUrl) {
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

  public RequireMine approveStatus(Integer approveStatus) {
    this.approveStatus = approveStatus;
    return this;
  }

  @ApiModelProperty(example = "20", value = "需求承接人数(当条件为我发布时 有值)")
  public Integer getAcceptNum() {
    return acceptNum;
  }

  public void setAcceptNum(Integer acceptNum) {
    this.acceptNum = acceptNum;
  }

  /**
   * 审核状态：0:未审核，1:通过，2:拒绝
   * minimum: 0
   * maximum: 2
   * @return approveStatus
  **/
  @ApiModelProperty(example = "1", value = "审核状态：0:未审核，1:通过，2:拒绝")
  @Min(0)
  @Max(2)
  public Integer getApproveStatus() {
    return approveStatus;
  }

  public void setApproveStatus(Integer approveStatus) {
    this.approveStatus = approveStatus;
  }

  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequireMine requireMine = (RequireMine) o;
    return Objects.equals(this.requireBase, requireMine.requireBase) &&
        Objects.equals(this.coverImgUrl, requireMine.coverImgUrl) &&
        Objects.equals(this.approveStatus, requireMine.approveStatus);
  }




  @Override
  public int hashCode() {
    return Objects.hash(requireBase, coverImgUrl, approveStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequireMine {\n");
    
    sb.append("    requireBase: ").append(toIndentedString(requireBase)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    approveStatus: ").append(toIndentedString(approveStatus)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


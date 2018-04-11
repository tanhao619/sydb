package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * IntelPatentDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class PatentDetail   {
  @JsonProperty("patent")
  private Patent patent = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public PatentDetail patent(Patent patent) {
    this.patent = patent;
    return this;
  }

   /**
   * Get patent
   * @return patent
  **/
  @ApiModelProperty(value = "")
  public Patent getPatent() {
    return patent;
  }

  public void setPatent(Patent patent) {
    this.patent = patent;
  }

  public PatentDetail publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03", value = "发布时间")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public PatentDetail viewCount(Integer viewCount) {
    this.viewCount = viewCount;
    return this;
  }

   /**
   * 浏览量
   * @return viewCount
  **/
  @ApiModelProperty(example = "300", value = "浏览量")
  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatentDetail intelPatentDetail = (PatentDetail) o;
    return Objects.equals(this.patent, intelPatentDetail.patent) &&
        Objects.equals(this.publishTime, intelPatentDetail.publishTime) &&
        Objects.equals(this.viewCount, intelPatentDetail.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(patent, publishTime, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelPatentDetail {\n");
    
    sb.append("    patent: ").append(toIndentedString(patent)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    viewCount: ").append(toIndentedString(viewCount)).append("\n");
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


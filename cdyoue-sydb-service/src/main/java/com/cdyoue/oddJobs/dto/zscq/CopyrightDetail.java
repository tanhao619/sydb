package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * CopyrightDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class CopyrightDetail   {
  @JsonProperty("copyright")
  private Copyright copyright = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public CopyrightDetail copyright(Copyright copyright) {
    this.copyright = copyright;
    return this;
  }

   /**
   * Get copyright
   * @return copyright
  **/
  @ApiModelProperty(value = "")
  public Copyright getCopyright() {
    return copyright;
  }

  public void setCopyright(Copyright copyright) {
    this.copyright = copyright;
  }

  public CopyrightDetail publishTime(String publishTime) {
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

  public CopyrightDetail viewCount(Integer viewCount) {
    this.viewCount = viewCount;
    return this;
  }

   /**
   * 浏览次数
   * @return viewCount
  **/
  @ApiModelProperty(example = "300", value = "浏览次数")
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
    CopyrightDetail copyrightDetail = (CopyrightDetail) o;
    return Objects.equals(this.copyright, copyrightDetail.copyright) &&
        Objects.equals(this.publishTime, copyrightDetail.publishTime) &&
        Objects.equals(this.viewCount, copyrightDetail.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(copyright, publishTime, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CopyrightDetail {\n");
    
    sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n");
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


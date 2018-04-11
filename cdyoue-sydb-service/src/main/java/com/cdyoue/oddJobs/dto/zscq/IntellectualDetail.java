package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * IntellectualDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class IntellectualDetail   {
  @JsonProperty("Intellectual")
  private Intellectual intellectual = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("infoSource")
  private String infoSource = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public IntellectualDetail intellectual(Intellectual intellectual) {
    this.intellectual = intellectual;
    return this;
  }

   /**
   * Get intellectual
   * @return intellectual
  **/
  @ApiModelProperty(value = "")
  public Intellectual getIntellectual() {
    return intellectual;
  }

  public void setIntellectual(Intellectual intellectual) {
    this.intellectual = intellectual;
  }

  public IntellectualDetail publishTime(String publishTime) {
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

  public IntellectualDetail infoSource(String infoSource) {
    this.infoSource = infoSource;
    return this;
  }

   /**
   * 信息来源
   * @return infoSource
  **/
  @ApiModelProperty(example = "北京", value = "信息来源")
  public String getInfoSource() {
    return infoSource;
  }

  public void setInfoSource(String infoSource) {
    this.infoSource = infoSource;
  }

  public IntellectualDetail viewCount(Integer viewCount) {
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
    IntellectualDetail intellectualDetail = (IntellectualDetail) o;
    return Objects.equals(this.intellectual, intellectualDetail.intellectual) &&
        Objects.equals(this.publishTime, intellectualDetail.publishTime) &&
        Objects.equals(this.infoSource, intellectualDetail.infoSource) &&
        Objects.equals(this.viewCount, intellectualDetail.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(intellectual, publishTime, infoSource, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntellectualDetail {\n");
    
    sb.append("    intellectual: ").append(toIndentedString(intellectual)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    infoSource: ").append(toIndentedString(infoSource)).append("\n");
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


package com.cdyoue.oddJobs.dto.ggfw;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * NewsDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T11:27:32.880Z")

public class NewsDetail   {
  @JsonProperty("newsBase")
  private NewsRequest newsBase = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("infoSource")
  private String infoSource = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public NewsDetail newsBase(NewsRequest newsBase) {
    this.newsBase = newsBase;
    return this;
  }

   /**
   * Get newsBase
   * @return newsBase
  **/
  @ApiModelProperty(value = "")
  public NewsRequest getNewsBase() {
    return newsBase;
  }

  public void setNewsBase(NewsRequest newsBase) {
    this.newsBase = newsBase;
  }

  public NewsDetail publishTime(String publishTime) {
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

  public NewsDetail infoSource(String infoSource) {
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

  public NewsDetail viewCount(Integer viewCount) {
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
    NewsDetail newsDetail = (NewsDetail) o;
    return Objects.equals(this.newsBase, newsDetail.newsBase) &&
        Objects.equals(this.publishTime, newsDetail.publishTime) &&
        Objects.equals(this.infoSource, newsDetail.infoSource) &&
        Objects.equals(this.viewCount, newsDetail.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newsBase, publishTime, infoSource, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsDetail {\n");
    
    sb.append("    newsBase: ").append(toIndentedString(newsBase)).append("\n");
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


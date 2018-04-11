package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * TrademarkDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class TrademarkDetail   {
  @JsonProperty("trademark")
  private Trademark trademark = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public TrademarkDetail trademark(Trademark trademark) {
    this.trademark = trademark;
    return this;
  }

   /**
   * Get trademark
   * @return trademark
  **/
  @ApiModelProperty(value = "")
  public Trademark getTrademark() {
    return trademark;
  }

  public void setTrademark(Trademark trademark) {
    this.trademark = trademark;
  }

  public TrademarkDetail publishTime(String publishTime) {
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

  public TrademarkDetail viewCount(Integer viewCount) {
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
    TrademarkDetail trademarkDetail = (TrademarkDetail) o;
    return Objects.equals(this.trademark, trademarkDetail.trademark) &&
        Objects.equals(this.publishTime, trademarkDetail.publishTime) &&
        Objects.equals(this.viewCount, trademarkDetail.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trademark, publishTime, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrademarkDetail {\n");
    
    sb.append("    trademark: ").append(toIndentedString(trademark)).append("\n");
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


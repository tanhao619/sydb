package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * PatentBigdataPriceComp
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

public class PatentBigdataPriceComp   {



  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("link")
  private String link = null;

  @JsonProperty("sources")
  private String sources = null;

  @JsonProperty("trade")
  private String trade = null;

  @JsonProperty("referPrice")
  private String referPrice = null;

  @JsonProperty("publishDate")
  private String publishDate = null;

  public PatentBigdataPriceComp sources(String sources) {
    this.sources = sources;
    return this;
  }

   /**
   * 来源
   * @return sources
  **/
  @ApiModelProperty(example = "一种聚义", value = "来源")
  public String getSources() {
    return sources;
  }

  public void setSources(String sources) {
    this.sources = sources;
  }

  public PatentBigdataPriceComp trade(String trade) {
    this.trade = trade;
    return this;
  }

   /**
   * 产业化指数交易方式
   * @return trade
  **/
  @ApiModelProperty(example = "222", value = "产业化指数交易方式")
  public String getTrade() {
    return trade;
  }

  public void setTrade(String trade) {
    this.trade = trade;
  }

  public PatentBigdataPriceComp referPrice(String referPrice) {
    this.referPrice = referPrice;
    return this;
  }

   /**
   * 参考价
   * @return referPrice
  **/
  @ApiModelProperty(example = "111", value = "参考价")
  public String getReferPrice() {
    return referPrice;
  }

  public void setReferPrice(String referPrice) {
    this.referPrice = referPrice;
  }

  public PatentBigdataPriceComp publishDate(String publishDate) {
    this.publishDate = publishDate;
    return this;
  }

   /**
   * 发布时间
   * @return publishDate
  **/
  @ApiModelProperty(example = "2017/03/03", value = "发布时间")
  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  /**
   * 比价信息id
   * @return id
   **/
  @ApiModelProperty(example = "123", value = "比价信息id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 网站链接
   * @return link
   **/
  @ApiModelProperty(example = "http://www.jd.com", value = "网站链接")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatentBigdataPriceComp patentBigdataPriceComp = (PatentBigdataPriceComp) o;
    return Objects.equals(this.sources, patentBigdataPriceComp.sources) &&
        Objects.equals(this.trade, patentBigdataPriceComp.trade) &&
        Objects.equals(this.referPrice, patentBigdataPriceComp.referPrice) &&
        Objects.equals(this.publishDate, patentBigdataPriceComp.publishDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sources, trade, referPrice, publishDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatentBigdataPriceComp {\n");
    
    sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
    sb.append("    trade: ").append(toIndentedString(trade)).append("\n");
    sb.append("    referPrice: ").append(toIndentedString(referPrice)).append("\n");
    sb.append("    publishDate: ").append(toIndentedString(publishDate)).append("\n");
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


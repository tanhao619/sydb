package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 知产出售
 */
@ApiModel(description = "知产出售")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class IntellectualSale   {
  @JsonProperty("category")
  private Integer category = null;

  @JsonProperty("patent")
  private Patent patent = null;

  @JsonProperty("trademark")
  private Trademark trademark = null;

  @JsonProperty("copyright")
  private Copyright copyright = null;

  public IntellectualSale category(Integer category) {
    this.category = category;
    return this;
  }

   /**
   * 发布的知识产权类别：1商标，2专利，3著作权
   * @return category
  **/
  @ApiModelProperty(example = "1", value = "发布的知识产权类别：1商标，2专利，3著作权")
  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public IntellectualSale patent(Patent patent) {
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

  public IntellectualSale trademark(Trademark trademark) {
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

  public IntellectualSale copyright(Copyright copyright) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntellectualSale intellectualSale = (IntellectualSale) o;
    return Objects.equals(this.category, intellectualSale.category) &&
        Objects.equals(this.patent, intellectualSale.patent) &&
        Objects.equals(this.trademark, intellectualSale.trademark) &&
        Objects.equals(this.copyright, intellectualSale.copyright);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, patent, trademark, copyright);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntellectualSale {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    patent: ").append(toIndentedString(patent)).append("\n");
    sb.append("    trademark: ").append(toIndentedString(trademark)).append("\n");
    sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n");
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


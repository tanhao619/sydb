package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.common.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * RequireSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireSummary   {
  @JsonProperty("requireBase")
  private RequireBase requireBase = null;

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("employerId")
  private Integer employerId = null;

  @JsonProperty("employerName")
  private String employerName = null;

  public RequireSummary requireBase(RequireBase requireBase) {
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

  public RequireSummary category(Category category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public RequireSummary introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 需求介绍
   * @return introduction
  **/
  @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "需求介绍")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public RequireSummary coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * 封面 （AW）
   * @return coverImgUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面 （AW）")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }

  public RequireSummary employerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

   /**
   * 发布需求公司的名称（A）
   * @return employerName
  **/
  @ApiModelProperty(example = "国信优易数据有限公司", value = "发布需求公司的名称（A）")
  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

    @ApiModelProperty(example = "5", value = "发布需求公司的ID")
    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequireSummary requireSummary = (RequireSummary) o;
    return Objects.equals(this.requireBase, requireSummary.requireBase) &&
        Objects.equals(this.category, requireSummary.category) &&
        Objects.equals(this.introduction, requireSummary.introduction) &&
        Objects.equals(this.coverImgUrl, requireSummary.coverImgUrl) &&
        Objects.equals(this.employerName, requireSummary.employerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requireBase, category, introduction, coverImgUrl, employerName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequireSummary {\n");
    
    sb.append("    requireBase: ").append(toIndentedString(requireBase)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    employerName: ").append(toIndentedString(employerName)).append("\n");
    sb.append("}");
    return sb.toString();
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


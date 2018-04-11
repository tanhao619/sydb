package com.cdyoue.oddJobs.dto;

import com.cdyoue.oddJobs.dto.common.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * EnterpBaseInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class EnterpBaseInfo   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  public EnterpBaseInfo id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EnterpBaseInfo category(Category category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public EnterpBaseInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 企业名称
   * @return name
  **/
  @ApiModelProperty(example = "国信优易数据有限公司", value = "企业名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EnterpBaseInfo introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 企业简介
   * @return introduction
  **/
  @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "企业简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public EnterpBaseInfo coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * 企业封面
   * @return coverImgUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "企业封面")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpBaseInfo enterpBaseInfo = (EnterpBaseInfo) o;
    return Objects.equals(this.id, enterpBaseInfo.id) &&
        Objects.equals(this.category, enterpBaseInfo.category) &&
        Objects.equals(this.name, enterpBaseInfo.name) &&
        Objects.equals(this.introduction, enterpBaseInfo.introduction) &&
        Objects.equals(this.coverImgUrl, enterpBaseInfo.coverImgUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, name, introduction, coverImgUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnterpBaseInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
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


package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Objects;

/**
 * Content
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Content extends HashMap<String, com.cdyoue.oddJobs.dto.common.HttpMessage>  {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("moduleType")
  private String moduleType = null;

  public Content id(Integer id) {
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

  public Content name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 名称
   * @return name
  **/
  @ApiModelProperty(required = true, value = "名称")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Content url(String url) {
    this.url = url;
    return this;
  }

   /**
   * 链接地址
   * @return url
  **/
  @ApiModelProperty(value = "链接地址")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Content imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * 图片地址
   * @return imageUrl
  **/
  @ApiModelProperty(value = "图片地址")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Content title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(value = "标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Content moduleType(String moduleType) {
    this.moduleType = moduleType;
    return this;
  }

   /**
   * 模块类型
   * @return moduleType
  **/
  @ApiModelProperty(value = "模块类型")
  public String getModuleType() {
    return moduleType;
  }

  public void setModuleType(String moduleType) {
    this.moduleType = moduleType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Content content = (Content) o;
    return Objects.equals(this.id, content.id) &&
        Objects.equals(this.name, content.name) &&
        Objects.equals(this.url, content.url) &&
        Objects.equals(this.imageUrl, content.imageUrl) &&
        Objects.equals(this.title, content.title) &&
        Objects.equals(this.moduleType, content.moduleType) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, url, imageUrl, title, moduleType, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Content {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    moduleType: ").append(toIndentedString(moduleType)).append("\n");
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


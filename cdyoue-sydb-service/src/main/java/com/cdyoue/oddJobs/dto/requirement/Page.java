package com.cdyoue.oddJobs.dto.requirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Page
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Page   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("moduleType")
  private String moduleType = null;

  public Page id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * id
   * @return id
  **/
  @ApiModelProperty(example = "123", value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Page name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 名称或者名字
   * @return name
  **/
  @ApiModelProperty(example = "运动健身", value = "名称或者名字")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Page url(String url) {
    this.url = url;
    return this;
  }

   /**
   * 详情链接地址
   * @return url
  **/
  @ApiModelProperty(example = "http://youedata.com/a/project/index.html", value = "详情链接地址")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Page imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * 图片地址
   * @return imageUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "图片地址")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Page intro(String intro) {
    this.intro = intro;
    return this;
  }

   /**
   * 简介
   * @return intro
  **/
  @ApiModelProperty(example = "拉卡拉手环，是一款多卡合一的创新平台级可穿戴智能设备。支持 PBOC3.0 标准的电子现金卡、接入支持的全国各地一卡通和各类型的行业卡，方便消费者在全", value = "简介")
  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public Page moduleType(String moduleType) {
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
    Page page = (Page) o;
    return Objects.equals(this.id, page.id) &&
        Objects.equals(this.name, page.name) &&
        Objects.equals(this.url, page.url) &&
        Objects.equals(this.imageUrl, page.imageUrl) &&
        Objects.equals(this.intro, page.intro) &&
        Objects.equals(this.moduleType, page.moduleType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, url, imageUrl, intro, moduleType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Page {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    intro: ").append(toIndentedString(intro)).append("\n");
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


package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PageInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class PageInfo   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("cover_img")
  private String coverImg = null;

  @JsonProperty("link")
  private String link = null;

  /**
   * 获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务
   */
  public enum TypeEnum {
    CASE("case"),
    
    BPRO("bpro"),
    
    ZSCQFW("zscqfw"),
    
    SCFWJG("scfwjg"),
    
    GOVSERV("govServ");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("addProperties")
  private List<KeyValue> addProperties = new ArrayList<KeyValue>();

  public PageInfo title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 名称或者标题
   * @return title
  **/
  @ApiModelProperty(example = "大数据创新创业", value = "名称或者标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PageInfo intro(String intro) {
    this.intro = intro;
    return this;
  }

   /**
   * 简介
   * @return intro
  **/
  @ApiModelProperty(example = "十年之前，肯定不会有人想到，智能手机会在人们的生活中扮演着如此重要的地位。如今，智能手机已经不仅仅是人们彼此间沟通的媒介", value = "简介")
  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public PageInfo coverImg(String coverImg) {
    this.coverImg = coverImg;
    return this;
  }

   /**
   * 封面图片地址
   * @return coverImg
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面图片地址")
  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }

  public PageInfo link(String link) {
    this.link = link;
    return this;
  }

   /**
   * 详细信息链接地址
   * @return link
  **/
  @ApiModelProperty(example = "http://172.16.0.102/article.html", value = "详细信息链接地址")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public PageInfo type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * 获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务
   * @return type
  **/
  @ApiModelProperty(example = "case", value = "获取各个页面列表信息，页面包括：case:成功案例，bpro:大项目，zscqfw:知识产权服务，scfwjg:双创服务机构，govServ：政府服务")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public PageInfo addProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
    return this;
  }

  public PageInfo addAddPropertiesItem(KeyValue addPropertiesItem) {
    this.addProperties.add(addPropertiesItem);
    return this;
  }

   /**
   * name = serviceType（双创服务机构-服务内容）
   * @return addProperties
  **/
  @ApiModelProperty(value = "name = serviceType（双创服务机构-服务内容）")
  public List<KeyValue> getAddProperties() {
    return addProperties;
  }

  public void setAddProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageInfo pageInfo = (PageInfo) o;
    return Objects.equals(this.title, pageInfo.title) &&
        Objects.equals(this.intro, pageInfo.intro) &&
        Objects.equals(this.coverImg, pageInfo.coverImg) &&
        Objects.equals(this.link, pageInfo.link) &&
        Objects.equals(this.type, pageInfo.type) &&
        Objects.equals(this.addProperties, pageInfo.addProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, intro, coverImg, link, type, addProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageInfo {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    intro: ").append(toIndentedString(intro)).append("\n");
    sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addProperties: ").append(toIndentedString(addProperties)).append("\n");
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


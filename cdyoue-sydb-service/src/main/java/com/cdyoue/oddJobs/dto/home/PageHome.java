package com.cdyoue.oddJobs.dto.home;

import com.cdyoue.oddJobs.dto.common.KeyValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * PageHome
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T01:21:59.849Z")

public class PageHome   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("sort")
  private Double sort = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("cover_img")
  private String coverImg = null;

  @JsonProperty("link")
  private String link = null;
  @JsonProperty("id")
  private Integer id;
  @JsonProperty("creator")
  private String creator;
  @JsonProperty("createTime")
  private Timestamp createTime;

  @JsonProperty("addProperties")
  private List<KeyValue> addProperties = new ArrayList<KeyValue>();

  public PageHome name(String name) {
    this.name = name;
    return this;
  }
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 页面区域名称
   * @return name
  **/
  @ApiModelProperty(example = "创新成果", value = "页面区域名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PageHome sort(Double sort) {
    this.sort = sort;
    return this;
  }

   /**
   * 排序数字，数字越小的排在前面，仅用于编辑时，查询时后台已经按照从小到大顺序处理好了
   * @return sort
  **/
  @ApiModelProperty(example = "1", value = "排序数字，数字越小的排在前面，仅用于编辑时，查询时后台已经按照从小到大顺序处理好了")
  public Double getSort() {
    return sort;
  }

  public void setSort(Double sort) {
    this.sort = sort;
  }

  public PageHome title(String title) {
    this.title = title;
    return this;
  }
  @ApiModelProperty(name = "创建人")
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
  @ApiModelProperty(name = "创建时间")
  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
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

  public PageHome intro(String intro) {
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

  public PageHome coverImg(String coverImg) {
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

  public PageHome link(String link) {
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

  public PageHome addProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
    return this;
  }

  public PageHome addAddPropertiesItem(KeyValue addPropertiesItem) {
    this.addProperties.add(addPropertiesItem);
    return this;
  }


  /**
   * 页面其他属性信息
   * @return addProperties
  **/
  @ApiModelProperty(value = "页面其他属性信息")
  public List<KeyValue> getAddProperties() {
    return addProperties;
  }

  public void setAddProperties(List<KeyValue> addProperties) {
    this.addProperties = addProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageHome pageHome = (PageHome) o;
    return Objects.equals(this.name, pageHome.name) &&
        Objects.equals(this.sort, pageHome.sort) &&
        Objects.equals(this.title, pageHome.title) &&
        Objects.equals(this.intro, pageHome.intro) &&
        Objects.equals(this.coverImg, pageHome.coverImg) &&
        Objects.equals(this.link, pageHome.link) &&
        Objects.equals(this.addProperties, pageHome.addProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, sort, title, intro, coverImg, link, addProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageHome {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    intro: ").append(toIndentedString(intro)).append("\n");
    sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    addProperties: ").append(toIndentedString(addProperties)).append("\n");
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


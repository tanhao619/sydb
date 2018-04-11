package com.cdyoue.oddJobs.dto.requirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * PageBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class PageBase   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("cover_img")
  private String coverImg = null;

  @JsonProperty("link")
  private String link = null;

  public PageBase title(String title) {
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

  public PageBase intro(String intro) {
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

  public PageBase coverImg(String coverImg) {
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

  public PageBase link(String link) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageBase pageBase = (PageBase) o;
    return Objects.equals(this.title, pageBase.title) &&
        Objects.equals(this.intro, pageBase.intro) &&
        Objects.equals(this.coverImg, pageBase.coverImg) &&
        Objects.equals(this.link, pageBase.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, intro, coverImg, link);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageBase {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    intro: ").append(toIndentedString(intro)).append("\n");
    sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
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


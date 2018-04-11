package com.cdyoue.oddJobs.dto.xqdt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * SuccaseSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class SuccaseSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("createBy")
  private Integer createBy = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  @JsonProperty("publishPepole")
  private String publishPepole = null;

  public SuccaseSummary info(String info) {
    this.info = info;
    return this;
  }

  /**
   * 详细信息
   * @return info
   **/
  @ApiModelProperty(example = "info", value = "info")
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public SuccaseSummary createBy(Integer createBy) {
    this.createBy = createBy;
    return this;
  }

  /**
   * 创建人，自动生成
   * @return createBy
   **/
  @ApiModelProperty(value = "createBy")
  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
    this.createBy = createBy;
  }

  public SuccaseSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 成功案例id，自动生成
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SuccaseSummary title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 名称
   * @return title
  **/
  @ApiModelProperty(example = "卡拉卡", value = "名称")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public SuccaseSummary coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * 封面
   * @return coverImgUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }

  public SuccaseSummary introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 简介
   * @return introduction
  **/
  @ApiModelProperty(example = "个人简介个人简介个人简介个人简介", value = "简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public SuccaseSummary publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间（admin）
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "发布时间（admin）")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public SuccaseSummary viewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
    return this;
  }

   /**
   * 阅读量（admin）
   * @return viewsCount
  **/
  @ApiModelProperty(example = "10000", value = "阅读量（admin）")
  public Integer getViewsCount() {
    return viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }

  public SuccaseSummary publishPepole(String publishPepole) {
    this.publishPepole = publishPepole;
    return this;
  }

   /**
   * 发布人（admin）
   * @return publishPepole
  **/
  @ApiModelProperty(example = "admin", value = "发布人（admin）")
  public String getPublishPepole() {
    return publishPepole;
  }

  public void setPublishPepole(String publishPepole) {
    this.publishPepole = publishPepole;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuccaseSummary succaseSummary = (SuccaseSummary) o;
    return Objects.equals(this.id, succaseSummary.id) &&
        Objects.equals(this.title, succaseSummary.title) &&
        Objects.equals(this.coverImgUrl, succaseSummary.coverImgUrl) &&
        Objects.equals(this.introduction, succaseSummary.introduction) &&
        Objects.equals(this.publishTime, succaseSummary.publishTime) &&
        Objects.equals(this.viewsCount, succaseSummary.viewsCount) &&
        Objects.equals(this.publishPepole, succaseSummary.publishPepole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, coverImgUrl, introduction, publishTime, viewsCount, publishPepole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuccaseSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    viewsCount: ").append(toIndentedString(viewsCount)).append("\n");
    sb.append("    publishPepole: ").append(toIndentedString(publishPepole)).append("\n");
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


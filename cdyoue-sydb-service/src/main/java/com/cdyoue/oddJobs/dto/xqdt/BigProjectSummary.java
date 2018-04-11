package com.cdyoue.oddJobs.dto.xqdt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * BigFinProjectSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class BigProjectSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("type")
  private Integer type = null;

  @JsonProperty("rent")
  private String rent = null;

  @JsonProperty("number")
  private Integer number = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  @JsonProperty("publishPepole")
  private String publishPepole = null;

  public BigProjectSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 大项目 id 自动生成
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigProjectSummary title(String title) {
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

  public BigProjectSummary coverImgUrl(String coverImgUrl) {
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

  public BigProjectSummary introduction(String introduction) {
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

  public BigProjectSummary type(Integer type) {
    this.type = type;
    return this;
  }

   /**
   * 出租方式,1 场地出租 2 工位出租
   * @return type
  **/
  @ApiModelProperty(example = "1", value = "出租方式,1 场地出租 2 工位出租")
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public BigProjectSummary rent(String rent) {
    this.rent = rent;
    return this;
  }

   /**
   * 租金
   * @return rent
  **/
  @ApiModelProperty(example = "123", value = "租金")
  public String getRent() {
    return rent;
  }

  public void setRent(String rent) {
    this.rent = rent;
  }

  public BigProjectSummary number(Integer number) {
    this.number = number;
    return this;
  }

   /**
   * 数量
   * @return number
  **/
  @ApiModelProperty(example = "123", value = "数量")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigProjectSummary publishTime(String publishTime) {
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

  public BigProjectSummary viewsCount(Integer viewsCount) {
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

  public BigProjectSummary publishPepole(String publishPepole) {
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
    BigProjectSummary bigFinProjectSummary = (BigProjectSummary) o;
    return Objects.equals(this.id, bigFinProjectSummary.id) &&
        Objects.equals(this.title, bigFinProjectSummary.title) &&
        Objects.equals(this.coverImgUrl, bigFinProjectSummary.coverImgUrl) &&
        Objects.equals(this.introduction, bigFinProjectSummary.introduction) &&
        Objects.equals(this.type, bigFinProjectSummary.type) &&
        Objects.equals(this.rent, bigFinProjectSummary.rent) &&
        Objects.equals(this.number, bigFinProjectSummary.number) &&
        Objects.equals(this.publishTime, bigFinProjectSummary.publishTime) &&
        Objects.equals(this.viewsCount, bigFinProjectSummary.viewsCount) &&
        Objects.equals(this.publishPepole, bigFinProjectSummary.publishPepole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, coverImgUrl, introduction, type, rent, number, publishTime, viewsCount, publishPepole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BigFinProjectSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    rent: ").append(toIndentedString(rent)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
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


package com.cdyoue.oddJobs.dto.xqdt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * BigProjectDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

public class BigProjectDetail   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("status")
  private Integer status = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("publishPepole")
  private String publishPepole = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  @JsonProperty("isTop")
  private Integer isTop = null;

  @JsonProperty("topImg")
  private String topImg = null;

  /**
   * 置顶图片
   * @return topImg
   **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "置顶图片")
  public String getTopImg() {
    return topImg;
  }

  public void setTopImg(String topImg) {
    this.topImg = topImg;
  }

  public BigProjectDetail name(String name) {
    this.name = name;
    return this;
  }

  public BigProjectDetail id(Integer id) {
    this.id = id;
    return this;
  }


  /**
   * 大项目 id, 自动创建
   * @return id
   **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

   /**
   * 项目名称
   * @return name
  **/
  @ApiModelProperty(example = "中海国际社区", value = "项目名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigProjectDetail coverImgUrl(String coverImgUrl) {
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

  public BigProjectDetail info(String info) {
    this.info = info;
    return this;
  }

  /**
   * 简介
   * @return info
   **/
  @ApiModelProperty(example = "个人简介个人简介个人简介个人简介", value = "简介")
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public BigProjectDetail introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 详细
   * @return introduction
  **/
  @ApiModelProperty(example = "详细详细详细", value = "详细")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public BigProjectDetail status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * 项目状态：1 进行中，2 已结束
   * @return status
  **/
  @ApiModelProperty(example = "1", value = "项目状态：1 进行中，2 已结束")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public BigProjectDetail price(String price) {
    this.price = price;
    return this;
  }

   /**
   * 金额
   * @return price
  **/
  @ApiModelProperty(example = "123", value = "金额")
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public BigProjectDetail isTop(Integer isTop) {
    this.isTop = isTop;
    return this;
  }

   /**
   * 是否置顶（admin） 1 是， 0 否
   * @return isTop
  **/
  @ApiModelProperty(example = "1", value = "是否置顶（admin） 1 是， 0 否")
  public Integer getIsTop() {
    return isTop;
  }

  public void setIsTop(Integer isTop) {
    this.isTop = isTop;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BigProjectDetail bigProjectDetail = (BigProjectDetail) o;
    return Objects.equals(this.name, bigProjectDetail.name) &&
        Objects.equals(this.coverImgUrl, bigProjectDetail.coverImgUrl) &&
        Objects.equals(this.introduction, bigProjectDetail.introduction) &&
        Objects.equals(this.status, bigProjectDetail.status) &&
        Objects.equals(this.price, bigProjectDetail.price) &&
        Objects.equals(this.isTop, bigProjectDetail.isTop);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, coverImgUrl, introduction, status, price, isTop);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BigProjectDetail {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    isTop: ").append(toIndentedString(isTop)).append("\n");
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

  public BigProjectDetail viewsCount(Integer viewsCount) {
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

  public BigProjectDetail publishPepole(String publishPepole) {
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

  public BigProjectDetail publishTime(String publishTime) {
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
}


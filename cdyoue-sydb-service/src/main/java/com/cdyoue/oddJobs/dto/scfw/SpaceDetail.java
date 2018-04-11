package com.cdyoue.oddJobs.dto.scfw;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * SpaceDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class SpaceDetail   {
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

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("contactor")
  private String contactor = null;

  @JsonProperty("contact")
  private String contact = null;

  @JsonProperty("area")
  private Integer area=null;

  @JsonProperty("areaNext")
  private Integer areaNext=null;

  public SpaceDetail title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 名称
   * @return title
  **/
  @ApiModelProperty(example = "中海国际社区", value = "名称")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public SpaceDetail coverImgUrl(String coverImgUrl) {
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

  public SpaceDetail introduction(String introduction) {
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

  public SpaceDetail type(Integer type) {
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

  public SpaceDetail rent(String rent) {
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

  public SpaceDetail number(Integer number) {
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

  public SpaceDetail address(String address) {
    this.address = address;
    return this;
  }

   /**
   * 详细地址
   * @return address
  **/
  @ApiModelProperty(example = "四川省成都市碧林街99号", value = "详细地址")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public SpaceDetail contactor(String contactor) {
    this.contactor = contactor;
    return this;
  }

   /**
   * 联系人
   * @return contactor
  **/
  @ApiModelProperty(example = "刘杰", value = "联系人")
  public String getContactor() {
    return contactor;
  }

  public void setContactor(String contactor) {
    this.contactor = contactor;
  }

  public SpaceDetail contact(String contact) {
    this.contact = contact;
    return this;
  }

   /**
   * 联系方式
   * @return contact
  **/
  @ApiModelProperty(example = "13800000000", value = "联系方式")
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  /**
   *  所属一级区域
   * @return
   */
  @ApiModelProperty(example = "", value = "所属区域省级区域")
  public Integer getArea() {
    return area;
  }

  public void setArea(Integer area) {
    this.area = area;
  }

  /**
   * 所属二级区域
   * @return
   */
  @ApiModelProperty(example = "", value = "所属区域市级区域")
  public Integer getAreaNext() {
    return areaNext;
  }

  public void setAreaNext(Integer areaNext) {
    this.areaNext = areaNext;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpaceDetail spaceDetail = (SpaceDetail) o;
    return Objects.equals(this.title, spaceDetail.title) &&
        Objects.equals(this.coverImgUrl, spaceDetail.coverImgUrl) &&
        Objects.equals(this.introduction, spaceDetail.introduction) &&
        Objects.equals(this.type, spaceDetail.type) &&
        Objects.equals(this.rent, spaceDetail.rent) &&
        Objects.equals(this.number, spaceDetail.number) &&
        Objects.equals(this.address, spaceDetail.address) &&
        Objects.equals(this.contactor, spaceDetail.contactor) &&
        Objects.equals(this.contact, spaceDetail.contact)&&
            Objects.equals(this.area, spaceDetail.area);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, coverImgUrl, introduction, type, rent, number, address, contactor, contact,area);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpaceDetail {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    rent: ").append(toIndentedString(rent)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contactor: ").append(toIndentedString(contactor)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
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


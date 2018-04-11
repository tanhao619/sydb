package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 商标
 */
@ApiModel(description = "商标")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class Trademark   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("transactionType")
  private Integer transactionType = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("introduction")
  private String introduction = null;

  public Trademark title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 商标名称（最多50个字符）
   * @return title
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利", value = "商标名称（最多50个字符）")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Trademark content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 详细介绍（富文本）
   * @return content
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利工业门机、卷门及其遥控等相关的发明专利", value = "详细介绍（富文本）")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Trademark transactionType(Integer transactionType) {
    this.transactionType = transactionType;
    return this;
  }

   /**
   * 交易类型：1 转让，2 许可
   * @return transactionType
  **/
  @ApiModelProperty(example = "2", value = "交易类型：1 转让，2 许可")
  public Integer getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(Integer transactionType) {
    this.transactionType = transactionType;
  }

  public Trademark imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * 商标图示
   * @return imageUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "商标图示")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Trademark price(String price) {
    this.price = price;
    return this;
  }

   /**
   * 出售价格
   * @return price
  **/
  @ApiModelProperty(example = "12222", value = "出售价格")
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Trademark contact(Contact contact) {
    this.contact = contact;
    return this;
  }

   /**
   * Get contact
   * @return contact
  **/
  @ApiModelProperty(value = "")
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Trademark trademark = (Trademark) o;
    return Objects.equals(this.title, trademark.title) &&
        Objects.equals(this.content, trademark.content) &&
        Objects.equals(this.transactionType, trademark.transactionType) &&
        Objects.equals(this.imageUrl, trademark.imageUrl) &&
        Objects.equals(this.price, trademark.price) &&
        Objects.equals(this.contact, trademark.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, transactionType, imageUrl, price, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Trademark {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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

  /**
   *详细信息
   * @return
   */
  @ApiModelProperty(example = "详细信息", value = "详细信息")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
}


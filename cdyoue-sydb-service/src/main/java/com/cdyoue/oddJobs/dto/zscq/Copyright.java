package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Copyright
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class Copyright   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("transactionType")
  private Integer transactionType = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("introduction")
  private String introduction = null;

  public Copyright title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 著作权名称（最多50个字符）
   * @return title
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利", value = "著作权名称（最多50个字符）")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Copyright content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 详细要求（富文本）
   * @return content
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利工业门机、卷门及其遥控等相关的发明专利", value = "详细要求（富文本）")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Copyright price(String price) {
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

  public Copyright transactionType(Integer transactionType) {
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

  public Copyright contact(Contact contact) {
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
    Copyright copyright = (Copyright) o;
    return Objects.equals(this.title, copyright.title) &&
        Objects.equals(this.content, copyright.content) &&
        Objects.equals(this.price, copyright.price) &&
        Objects.equals(this.transactionType, copyright.transactionType) &&
        Objects.equals(this.contact, copyright.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, price, transactionType, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Copyright {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
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


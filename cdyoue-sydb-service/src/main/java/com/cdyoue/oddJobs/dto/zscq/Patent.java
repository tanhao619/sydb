package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Patent
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class Patent   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("PatentNo")
  private String patentNo = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("transactionType")
  private Integer transactionType = null;

  @JsonProperty("patentType")
  private Integer patentType = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("introduction")
  private String introduction = null;

  public Patent title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 专利名称
   * @return title
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利", value = "专利名称")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Patent patentNo(String patentNo) {
    this.patentNo = patentNo;
    return this;
  }

   /**
   * 专利申请号
   * @return patentNo
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利", value = "专利申请号")
  public String getPatentNo() {
    return patentNo;
  }

  public void setPatentNo(String patentNo) {
    this.patentNo = patentNo;
  }

  public Patent content(String content) {
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

  public Patent price(String price) {
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

  public Patent transactionType(Integer transactionType) {
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

  public Patent patentType(Integer patentType) {
    this.patentType = patentType;
    return this;
  }

   /**
   * 专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等
   * @return patentType
  **/
  @ApiModelProperty(example = "2", value = "专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等")
  public Integer getPatentType() {
    return patentType;
  }

  public void setPatentType(Integer patentType) {
    this.patentType = patentType;
  }

  public Patent contact(Contact contact) {
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
    Patent patent = (Patent) o;
    return Objects.equals(this.title, patent.title) &&
        Objects.equals(this.patentNo, patent.patentNo) &&
        Objects.equals(this.content, patent.content) &&
        Objects.equals(this.price, patent.price) &&
        Objects.equals(this.transactionType, patent.transactionType) &&
        Objects.equals(this.patentType, patent.patentType) &&
        Objects.equals(this.contact, patent.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, patentNo, content, price, transactionType, patentType, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patent {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    patentNo: ").append(toIndentedString(patentNo)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    patentType: ").append(toIndentedString(patentType)).append("\n");
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


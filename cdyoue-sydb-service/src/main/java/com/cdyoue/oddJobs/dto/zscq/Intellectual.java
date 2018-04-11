package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Intellectual
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

public class Intellectual   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("usage")
  private String usage = null;

  @JsonProperty("category")
  private Integer category = null;

  @JsonProperty("transactionType")
  private Integer transactionType = null;

  @JsonProperty("patentType")
  private Integer patentType = null;

  @JsonProperty("contact")
  private Contact contact = null;

  public Intellectual title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 求购标题（最多50个字符）
   * @return title
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利", value = "求购标题（最多50个字符）")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Intellectual content(String content) {
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

  public Intellectual usage(String usage) {
    this.usage = usage;
    return this;
  }

   /**
   * 计划用途
   * @return usage
  **/
  @ApiModelProperty(example = "工业门机、卷门及其遥控等相关的发明专利工业门机、卷门及其遥控等相关的发明专利", value = "计划用途")
  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }

  public Intellectual category(Integer category) {
    this.category = category;
    return this;
  }

   /**
   * 知识产权类别：1商标，2专利，3著作权
   * @return category
  **/
  @ApiModelProperty(example = "1", value = "知识产权类别：1商标，2专利，3著作权")
  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public Intellectual transactionType(Integer transactionType) {
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

  public Intellectual patentType(Integer patentType) {
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

  public Intellectual contact(Contact contact) {
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
    Intellectual intellectual = (Intellectual) o;
    return Objects.equals(this.title, intellectual.title) &&
        Objects.equals(this.content, intellectual.content) &&
        Objects.equals(this.usage, intellectual.usage) &&
        Objects.equals(this.category, intellectual.category) &&
        Objects.equals(this.transactionType, intellectual.transactionType) &&
        Objects.equals(this.patentType, intellectual.patentType) &&
        Objects.equals(this.contact, intellectual.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, usage, category, transactionType, patentType, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Intellectual {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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
}


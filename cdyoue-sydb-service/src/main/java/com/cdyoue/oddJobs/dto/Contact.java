package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Contact
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Contact   {
  @JsonProperty("person")
  private String person = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("mail")
  private String mail = null;

  @JsonProperty("introduction")
  private String introduction = null;

  public Contact person(String person) {
    this.person = person;
    return this;
  }

   /**
   * 联系人
   * @return person
  **/
  @ApiModelProperty(example = "王海涛", value = "联系人")
  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public Contact phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * 联系方式
   * @return phone
  **/
  @ApiModelProperty(example = "13800000000", value = "联系方式")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Contact introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 个人简介
   * @return introduction
  **/
  @ApiModelProperty(example = "个人简介个人简介个人简介个人简介", value = "个人简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  @ApiModelProperty(example = "个人邮件", value = "个人邮件")
  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.person, contact.person) &&
        Objects.equals(this.phone, contact.phone) &&
        Objects.equals(this.introduction, contact.introduction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, phone, introduction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");
    
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
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


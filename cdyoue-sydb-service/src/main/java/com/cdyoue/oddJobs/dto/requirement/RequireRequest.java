package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.dto.common.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * RequireRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireRequest   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("category")
  @NotNull
  private Category category = null;

  @JsonProperty("name")
  @NotNull
  @Size(min = 1,max = 50,message = "名字1~50个字符")
  private String name = null;

  @JsonProperty("introduction")
  @NotNull
  @Size(min = 1,max = 1000,message = "简介1~1000个字符")
  private String introduction = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("attachUrl")
  private String attachUrl = null;

  @JsonProperty("coverImgUrl")
  @NotNull
  @Size(min = 1,message = "图片必填")
  private String coverImgUrl = null;

  @JsonProperty("budget")
  @NotNull
  private BigDecimal budget = null;

  @JsonProperty("deadline")
  @NotNull
  private Integer deadline = null;

  public RequireRequest id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "需求主键ID")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RequireRequest category(Category category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }




  public RequireRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 需求名称
   * @return name
  **/
  @ApiModelProperty(example = "LOGO制作", required = true, value = "需求名称")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RequireRequest introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 需求介绍
   * @return introduction
  **/
  @ApiModelProperty(example = "XXXX类型的LOGO制作", value = "需求介绍")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }



   /**
   * 联系方式
   * @return contact
  **/
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public RequireRequest attachUrls(String attachUrl) {
    this.attachUrl = attachUrl;
    return this;
  }

   /**
   * 上传的附件，只能上传压缩包，rar或者zip格式，最大上传30M
   * @return attachUrls
  **/
  @ApiModelProperty(example = "http://172.16.0.102/attach.zip", value = "上传的附件，只能上传压缩包，rar或者zip格式，最大上传30M")
  public String getAttachUrl() {
    return attachUrl;
  }

  public void setAttachUrl(String attachUrl) {
    this.attachUrl = attachUrl;
  }

  public RequireRequest coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

   /**
   * Get coverImgUrl
   * @return coverImgUrl
  **/
  @ApiModelProperty(value = "图片地址")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }

  public RequireRequest budget(BigDecimal budget) {
    this.budget = budget;
    return this;
  }

   /**
   * 项目预算
   * @return budget
  **/
  @ApiModelProperty(example = "20", value = "项目预算")
  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public RequireRequest deadline(Integer deadline) {
    this.deadline = deadline;
    return this;
  }

   /**
   * 完成时限，单位天
   * @return deadline
  **/
  @ApiModelProperty(example = "20", value = "完成时限，单位天")
  public Integer getDeadline() {
    return deadline;
  }

  public void setDeadline(Integer deadline) {
    this.deadline = deadline;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequireRequest requireRequest = (RequireRequest) o;
    return Objects.equals(this.id, requireRequest.id) &&
        Objects.equals(this.category, requireRequest.category) &&
        Objects.equals(this.name, requireRequest.name) &&
        Objects.equals(this.introduction, requireRequest.introduction) &&
        Objects.equals(this.contact, requireRequest.contact) &&
        Objects.equals(this.attachUrl, requireRequest.attachUrl) &&
        Objects.equals(this.coverImgUrl, requireRequest.coverImgUrl) &&
        Objects.equals(this.budget, requireRequest.budget) &&
        Objects.equals(this.deadline, requireRequest.deadline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, name, introduction, contact, attachUrl, coverImgUrl, budget, deadline);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequireRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    attachUrl: ").append(toIndentedString(attachUrl)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    budget: ").append(toIndentedString(budget)).append("\n");
    sb.append("    deadline: ").append(toIndentedString(deadline)).append("\n");
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


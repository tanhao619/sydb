package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * 需求详细信息
 */
@ApiModel(description = "需求详细信息")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireDetails   {
  @JsonProperty("requireBase")
  private RequireBase requireBase = null;

  @JsonProperty("category")
  private Category category = null;



  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("attachUrl")
  private String attachUrl;

  @JsonProperty("attachName")
  private String attachName;

  @JsonProperty("attachId")
  private Integer attachId;

    @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("deadline")
  private Integer deadline = null;

  @JsonProperty("contact")
  private Contact contact;

  @JsonProperty("isOwner")
  private Boolean isOwner = false;

  public RequireDetails requireBase(RequireBase requireBase) {
    this.requireBase = requireBase;
    return this;
  }

   /**
   * Get requireBase
   * @return requireBase
  **/
  @ApiModelProperty(value = "")
  public RequireBase getRequireBase() {
    return requireBase;
  }

  public void setRequireBase(RequireBase requireBase) {
    this.requireBase = requireBase;
  }

  public RequireDetails category(Category category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }


  public RequireDetails introduction(String introduction) {
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
  @ApiModelProperty(value = "附件地址")
  public String getAttachUrl() {
    return attachUrl;
  }

  public void setAttachUrl(String attachUrl) {
    this.attachUrl = attachUrl;
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

  public RequireDetails deadline(Integer deadline) {
    this.deadline = deadline;
    return this;
  }

   /**
   * 完成时限
   * @return deadline
  **/
  @ApiModelProperty(example = "20", value = "完成时限")
  public Integer getDeadline() {
    return deadline;
  }

  public void setDeadline(Integer deadline) {
    this.deadline = deadline;
  }



  @ApiModelProperty("联系方式")
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }


    @ApiModelProperty("附件名")
    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    @ApiModelProperty("附件ID")
    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }

    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequireDetails requireDetails = (RequireDetails) o;
    return Objects.equals(this.requireBase, requireDetails.requireBase) &&
            Objects.equals(this.category, requireDetails.category) &&
            Objects.equals(this.introduction, requireDetails.introduction) &&
            Objects.equals(this.coverImgUrl, requireDetails.coverImgUrl) &&
            Objects.equals(this.deadline, requireDetails.deadline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requireBase, category, introduction, coverImgUrl, deadline);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequireDetails {\n");
    sb.append("    requireBase: ").append(toIndentedString(requireBase)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
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

  @ApiModelProperty("查看人是否是需求所属让你 0 否 1 是")
  public Boolean getIsOwner() {
    return isOwner;
  }

  public void setIsOwner(Boolean isOwner) {
    this.isOwner = isOwner;
  }
}


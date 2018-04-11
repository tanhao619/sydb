package com.cdyoue.oddJobs.dto.scfw;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * FinProjectDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class FinProjectDetail   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("finance")
  private String finance = null;

  @JsonProperty("attachUrl")
  private String attachUrl = null;

  @JsonProperty("attachName")
  private String attachName = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("publishTime")
  private String publishTime;

    @JsonProperty("reviewReason")
    private String reviewReason = null;

    /**
     * 活动 review reason
     * @return review reason
     **/
    @ApiModelProperty(example = "", value = "review reason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public FinProjectDetail reviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
        return this;
    }
  public FinProjectDetail title(String title) {
    this.title = title;
    return this;
  }


   /**
   * 项目名称
   * @return name
  **/
  @ApiModelProperty(example = "卡拉卡", value = "项目名称")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FinProjectDetail coverImgUrl(String coverImgUrl) {
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

  public FinProjectDetail introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 项目详细信息(富文本)
   * @return info
  **/
  @ApiModelProperty(example = "这是该项目的详情", value = "项目详细信息(富文本)")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public FinProjectDetail finance(String finance) {
    this.finance = finance;
    return this;
  }

   /**
   * 融资需求,单位万
   * @return finance
  **/
  @ApiModelProperty(example = "5000", value = "融资需求,单位万")
  public String getFinance() {
    return finance;
  }

  public void setFinance(String finance) {
    this.finance = finance;
  }

  public FinProjectDetail attachUrl(String attachUrl) {
    this.attachUrl = attachUrl;
    return this;
  }

   /**
   * 封面
   * @return attachUrl
  **/
  @ApiModelProperty(example = "Z:/lgsq/.jar/AuthManage-3.2_20170426_663e8c1e-60a9-4880-b147-9a251acdab46.jar", value = "附件地址")
  public String getAttachUrl() {
    return attachUrl;
  }

  public void setAttachUrl(String attachUrl) {
    this.attachUrl = attachUrl;
  }

  public FinProjectDetail info(String info) {
    this.info = info;
    return this;
  }

   /**
   * 项目简介)
   * @return info
  **/
  @ApiModelProperty(example = "项目简介", value = "项目j简介")
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public FinProjectDetail viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
  }

    /**
     * 浏览量
     * @return info
     **/

    @ApiModelProperty(example = "888", value = "浏览量")
    public Integer getViewCount() {
        return viewCount == null ? 0 : viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 联系方式
     * @return contact
     **/

    @ApiModelProperty(value = "联系方式")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @ApiModelProperty(example = "2017-3-10", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @ApiModelProperty(example = "苍老师.avi", value = "附件名称")
    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FinProjectDetail {\n");
    
    sb.append("    name: ").append(toIndentedString(title)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    finance: ").append(toIndentedString(finance)).append("\n");
    sb.append("    attachUrl: ").append(toIndentedString(attachUrl)).append("\n");
    sb.append("    content: ").append(toIndentedString(info)).append("\n");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinProjectDetail that = (FinProjectDetail) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (coverImgUrl != null ? !coverImgUrl.equals(that.coverImgUrl) : that.coverImgUrl != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (finance != null ? !finance.equals(that.finance) : that.finance != null) return false;
        if (attachUrl != null ? !attachUrl.equals(that.attachUrl) : that.attachUrl != null) return false;
        if (attachName != null ? !attachName.equals(that.attachName) : that.attachName != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (coverImgUrl != null ? coverImgUrl.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (finance != null ? finance.hashCode() : 0);
        result = 31 * result + (attachUrl != null ? attachUrl.hashCode() : 0);
        result = 31 * result + (attachName != null ? attachName.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        return result;
    }
}


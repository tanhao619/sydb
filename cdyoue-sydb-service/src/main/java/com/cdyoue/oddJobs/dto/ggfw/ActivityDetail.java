package com.cdyoue.oddJobs.dto.ggfw;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * ActivityDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-10T12:31:39.195Z")

public class ActivityDetail   {

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("peopleNumber")
  private String peopleNumber = null;

  @JsonProperty("briefing")
  private String briefing = null;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("reviewStatus")
  private String reviewStatus = null;

  @JsonProperty("reviewReason")
  private String reviewReason = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("forcusFlag")
  private String forcusFlag = null;

  @JsonProperty("createBy")
  private String createBy = null;

  /**
   * forcus Flag
   * @return forcus Flag
   **/
  @ApiModelProperty(example = "", value = "是否关注：0 未被关注; 1 已被关注")
  public String getForcusFlag() {
    return forcusFlag;
  }

  public void setForcusFlag(String forcusFlag) {
    this.forcusFlag = forcusFlag;
  }

  public ActivityDetail forcusFlag(String forcusFlag) {
    this.forcusFlag = forcusFlag;
    return this;
  }

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

  public ActivityDetail reviewReason(String reviewReason) {
    this.reviewReason = reviewReason;
    return this;
  }

  public ActivityDetail title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 活动 endtime
   * @return endtime
   **/
  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "endtime")
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public ActivityDetail endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }
  /**
   * Publish date
   * @return Publish date
   **/
  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "publishtime")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public ActivityDetail publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

  /**
   * 活动 review status
   * @return review status
   **/
  @ApiModelProperty(example = "0", value = "review status")
  public String getReviewStatus() {
    return reviewStatus;
  }

  public void setReviewStatus(String reviewStatus) {
    this.reviewStatus = reviewStatus;
  }

  public ActivityDetail reviewStatus(String reviewStatus) {
    this.reviewStatus = reviewStatus;
    return this;
  }

  /**
   * 活动名称标题,不少于5个字符
   * @return title
   **/
  @ApiModelProperty(example = "今天天气很好", value = "活动名称标题,不少于5个字符")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ActivityDetail content(String content) {
    this.content = content;
    return this;
  }

  /**
   * 活动详情（富文本）
   * @return content
   **/
  @ApiModelProperty(example = "今天天气很好,今天天气很好,今天天气很好,今天天气很好", value = "活动详情（富文本）")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ActivityDetail location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @ApiModelProperty(example = "四川成都高新区高新地铁站", value = "活动地址")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ActivityDetail startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * 发布时间
   * @return startTime
   **/
  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "发布时间")
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public ActivityDetail contact(Contact contact) {
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

  public ActivityDetail peopleNumber(String peopleNumber) {
    this.peopleNumber = peopleNumber;
    return this;
  }

  /**
   * 活动规模
   * @return peopleNumber
   **/
  @ApiModelProperty(example = "100", value = "活动人数（数字）")
  public String getPeopleNumber() {
    return peopleNumber;
  }

  public void setPeopleNumber(String peopleNumber) {
    this.peopleNumber = peopleNumber;
  }

  public ActivityDetail briefing(String briefing) {
    this.briefing = briefing;
    return this;
  }

  /**
   * 活动摘要
   * @return briefing
   **/
  @ApiModelProperty(example = "新闻摘要新闻摘要新闻摘要", value = "活动摘要")
  public String getBriefing() {
    return briefing;
  }

  public void setBriefing(String briefing) {
    this.briefing = briefing;
  }

  public ActivityDetail coverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
    return this;
  }

    @ApiModelProperty(example = "IBM", value = "发布单位")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
   * 活动封面
   * @return coverImgUrl
   **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "活动封面")
  public String getCoverImgUrl() {
    return coverImgUrl;
  }

  public void setCoverImgUrl(String coverImgUrl) {
    this.coverImgUrl = coverImgUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityDetail activity = (ActivityDetail) o;
    return Objects.equals(this.title, activity.title) &&
            Objects.equals(this.content, activity.content) &&
            Objects.equals(this.location, activity.location) &&
            Objects.equals(this.startTime, activity.startTime) &&
            Objects.equals(this.contact, activity.contact) &&
            Objects.equals(this.peopleNumber, activity.peopleNumber) &&
            Objects.equals(this.briefing, activity.briefing) &&
            Objects.equals(this.coverImgUrl, activity.coverImgUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content, location, startTime, contact, peopleNumber, briefing, coverImgUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class activityDetail {\n");

    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    peopleNumber: ").append(toIndentedString(peopleNumber)).append("\n");
    sb.append("    briefing: ").append(toIndentedString(briefing)).append("\n");
    sb.append("    coverImgUrl: ").append(toIndentedString(coverImgUrl)).append("\n");
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

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;


  public ActivityDetail viewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
    return this;
  }

   /**
   * 浏览次数
   * @return viewsCount
  **/
  @ApiModelProperty(example = "10000", value = "浏览次数")
  public Integer getViewsCount() {
    return viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }


}


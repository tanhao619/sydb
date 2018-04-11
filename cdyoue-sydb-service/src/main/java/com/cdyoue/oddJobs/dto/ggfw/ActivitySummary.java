package com.cdyoue.oddJobs.dto.ggfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Activity
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-10T12:31:39.195Z")

public class ActivitySummary {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("location")
    private String location = null;

    @JsonProperty("peopleNumber")
    private String peopleNumber = null;

    @JsonProperty("briefing")
    private String briefing = null;

    @JsonProperty("coverImgUrl")
    private String coverImgUrl = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("publishTime")
    private String publishTime = null;

    @JsonProperty("link")
    private String link = null;

    @JsonProperty("createBy")
    private String createBy = null;

    @JsonProperty("reviewStatus")
    private Byte reviewStatus = null;

    @JsonProperty("top")
    private Integer top;

    @JsonProperty("topImg")
    private String topImg;

    public ActivitySummary title(String title) {
        this.title = title;
        return this;
    }


    /**
     * Publish date
     *
     * @return Publish date
     **/
    @ApiModelProperty(example = "2017/03/03 12:00:00", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public ActivitySummary publishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    /**
     * 活动id
     *
     * @return id
     **/
    @ApiModelProperty(example = "11", value = "活动id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivitySummary id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 活动名称标题,不少于5个字符
     *
     * @return title
     **/
    @ApiModelProperty(example = "今天天气很好", value = "活动名称标题,不少于5个字符")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ActivitySummary peopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
        return this;
    }

    /**
     * 活动规模
     *
     * @return peopleNumber
     **/
    @ApiModelProperty(example = "100", value = "活动人数（数字）")
    public String getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public ActivitySummary briefing(String briefing) {
        this.briefing = briefing;
        return this;
    }

    /**
     * 活动摘要
     *
     * @return briefing
     **/
    @ApiModelProperty(example = "新闻摘要新闻摘要新闻摘要", value = "活动摘要")
    public String getBriefing() {
        return briefing;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }

    public ActivitySummary coverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
        return this;
    }

    /**
     * 活动封面
     *
     * @return coverImgUrl
     **/
    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "活动封面")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @ApiModelProperty(example = "PHILIPS", value = "发布企业")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @ApiModelProperty(example = "2017/03/03 12:00:00", value = "活动开始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @ApiModelProperty(example = "1", value = "审核状态：0审核中，1审核通过,2审核未通过")
    public Byte getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivitySummary activity = (ActivitySummary) o;
        return Objects.equals(this.title, activity.title) &&
                Objects.equals(this.location, activity.location) &&
                Objects.equals(this.peopleNumber, activity.peopleNumber) &&
                Objects.equals(this.briefing, activity.briefing) &&
                Objects.equals(this.coverImgUrl, activity.coverImgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, location, peopleNumber, briefing, coverImgUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Activity {\n");

        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
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
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public ActivitySummary location(String location) {
        this.location = location;
        return this;
    }

    /**
     * 活动地址
     *
     * @return 活动地址
     **/
    @ApiModelProperty(example = "江西宜春", value = "活动地址")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ApiModelProperty(example = "/H5/activityDetails.html?id=1234", value = "H5链接")
    public String getLink() {

        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }
}


package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * ProjectSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class ProjectSummary {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("publishPeople")
  private String publishPeople = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("department")
  private String department = null;

  @JsonProperty("startTime")
  private String startTime= null;

  @JsonProperty("endTime")
  private String endTime= null;

  @JsonProperty("attachmentUrl")
  private String attachmentUrl= null;

  @JsonProperty("viewCount")
  private Integer viewCount= null;


  public ProjectSummary id(Integer id) {
    this.id = id;
    return this;
  }
  /**
   * 项目浏览次数
   * @return viewCount
   **/
  @ApiModelProperty(example = "1000", value = "项目浏览次数")
  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  /**
   * 项目 id 自动生成
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ProjectSummary content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 申报内容
   * @return title
  **/
  @ApiModelProperty(example = "卡拉卡", value = "申报内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ProjectSummary name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 申报标题
   * @return title
   **/
  @ApiModelProperty(example = "卡拉卡", value = "申报标题")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectSummary department(String department) {
    this.department = department;
    return this;
  }

   /**
   * 申报部门
   * @return department
  **/
  @ApiModelProperty(example = "科技部", value = "申报部门")
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public ProjectSummary attachmentUrl(String attachmentUrl) {
    this.attachmentUrl = attachmentUrl;
    return this;
  }

   /**
   * 附件id
   * @return attachmentId
  **/
  @ApiModelProperty(example = "1", value = "附件id")
  public String getAttachmentUrl() {
    return attachmentUrl;
  }

  public void setAttachmentUrl(String attachmentUrl) {
    this.attachmentUrl = attachmentUrl;
  }

  public ProjectSummary publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间（admin）
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017-03-03", value = "发布时间（admin）")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public ProjectSummary startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * 申报开始时间
   * @return startTime
  **/
  @ApiModelProperty(example = "2017-03-03", value = "申报开始时间")
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public ProjectSummary endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * 申报结束时间
   * @return endTime
  **/
  @ApiModelProperty(example = "2017-03-09", value = "申报结束时间")
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public ProjectSummary publishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
    return this;
  }

  /**
   * 发布人
   * @return title
   **/
  @ApiModelProperty(example = "卡拉卡", value = "发布人")
  public String getPublishPeople() {
    return publishPeople;
  }

  public void setPublishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectSummary bigFinProjectSummary = (ProjectSummary) o;
    return Objects.equals(this.id, bigFinProjectSummary.id) &&
        Objects.equals(this.content, bigFinProjectSummary.content) &&
        Objects.equals(this.department, bigFinProjectSummary.department) &&
        Objects.equals(this.attachmentUrl, bigFinProjectSummary.attachmentUrl) &&
        Objects.equals(this.startTime, bigFinProjectSummary.startTime) &&
        Objects.equals(this.endTime, bigFinProjectSummary.endTime) &&
        Objects.equals(this.publishTime, bigFinProjectSummary.publishTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, department, attachmentUrl, startTime, endTime, publishTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectSummary {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    attachmentId: ").append(toIndentedString(attachmentUrl)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
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


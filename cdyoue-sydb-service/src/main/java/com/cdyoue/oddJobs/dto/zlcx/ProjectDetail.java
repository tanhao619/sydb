package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ProjectDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")

public class ProjectDetail {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("department")
  private String department = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("viewCount")
  private Integer viewCount= null;

  @JsonProperty("projectNumber")
  private String projectNumber= null;

  @JsonProperty("source")
  private String source= null;

  @JsonProperty("attachmentUrl")
  private String attachmentUrl= null;


  public ProjectDetail id(Integer id) {
    this.id = id;
    return this;
  }
  /**
   * 申报部门
   * @return title
   **/
  @ApiModelProperty(example = "卡拉卡", value = "申报部门")
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
  /**
   * 申报开始时间
   * @return title
   **/
  @ApiModelProperty(example = "2017-09-09", value = "申报开始时间")
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
  /**
   * 申报结束时间
   * @return title
   **/
  @ApiModelProperty(example = "2017-09-28", value = "申报结束时间")
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
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

  public ProjectDetail content(String content) {
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

  public ProjectDetail name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 申报项目名称
   * @return department
  **/
  @ApiModelProperty(example = "第一个项目", value = "申报项目名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectDetail viewCount(Integer viewCount) {
    this.viewCount = viewCount;
    return this;
  }

   /**
   * 浏览量
   * @return attachmentId
  **/
  @ApiModelProperty(example = "1000", value = "浏览量")
  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public ProjectDetail publishTime(String publishTime) {
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

  public ProjectDetail projectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
    return this;
  }

   /**
   * 发文号
   * @return startTime
  **/
  @ApiModelProperty(example = "00000000007", value = "发文号")
  public String getProjectNumber() {
    return projectNumber;
  }

  public void setProjectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
  }

  public ProjectDetail source(String source) {
    this.source = source;
    return this;
  }

   /**
   * 信息来源
   * @return endTime
  **/
  @ApiModelProperty(example = "优易大数据", value = "信息来源")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public ProjectDetail attachmentUrl(String attachmentUrl) {
    this.attachmentUrl = attachmentUrl;
    return this;
  }
  /**
   * 附件id
   * @return endTime
   **/
  @ApiModelProperty(example = "1", value = "附件id")
  public String getAttachmentUrl() {
    return attachmentUrl;
  }

  public void setAttachmentUrl(String attachmentUrl) {
    this.attachmentUrl = attachmentUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDetail bigFinProjectSummary = (ProjectDetail) o;
    return Objects.equals(this.id, bigFinProjectSummary.id) &&
        Objects.equals(this.content, bigFinProjectSummary.content) &&
        Objects.equals(this.name, bigFinProjectSummary.name) &&
        Objects.equals(this.projectNumber, bigFinProjectSummary.projectNumber) &&
        Objects.equals(this.source, bigFinProjectSummary.source) &&
        Objects.equals(this.viewCount, bigFinProjectSummary.viewCount) &&
        Objects.equals(this.publishTime, bigFinProjectSummary.publishTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, name, projectNumber, source, viewCount, publishTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectSummary {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    projectNumber: ").append(toIndentedString(projectNumber)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    viewCount: ").append(toIndentedString(viewCount)).append("\n");
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


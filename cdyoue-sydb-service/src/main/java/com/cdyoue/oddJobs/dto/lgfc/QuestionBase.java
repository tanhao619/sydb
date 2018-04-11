package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * QuestionBase
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class QuestionBase   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("PublicTime")
  private String publishTime = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("focus")
  private boolean focus=false;//该问题是否被关注，默认没有关注

  @JsonProperty("viewCount")
  private Integer viewCount=null;//查看量

  @JsonProperty("TopicId")
  private Integer topicId=null;//话题id

  public QuestionBase id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 问题id
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "问题id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public QuestionBase name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 话题ID
   * @return TopicId
   */
  @ApiModelProperty(example = "1", value = "话题ID")
  public Integer getTopicId() {
    return topicId;
  }

  public void setTopicId(Integer topicId) {
    this.topicId = topicId;
  }

  /**
   * 问题名称
   * @return name
  **/
  @ApiModelProperty(example = "怎样进行团队建设？", value = "问题名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public QuestionBase author(String author) {
    this.author = author;
    return this;
  }

   /**
   * 发布者
   * @return author
  **/
  @ApiModelProperty(example = "王小丫", value = "发布者")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public QuestionBase publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

   /**
   * 发布时间
   * @return publishTime
  **/
  @ApiModelProperty(example = "2017/03/03", value = "发布时间")
  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public QuestionBase content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 问题内容
   * @return content
  **/
  @ApiModelProperty(example = "除了欧文，还有谁向科比发出过这样的挑战", value = "问题内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 问题查看量
   * @return
   */
  @ApiModelProperty(example ="213",value = "查看量")
  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  /**
   * 问题是否被关注
   * @return
   */
  @ApiModelProperty(example = "false", value = "问题是否被关注")
  public boolean isFocus() {
    return focus;
  }

  public QuestionBase setFocus(boolean focus) {
    this.focus = focus;
    return this;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionBase questionBase = (QuestionBase) o;
    return Objects.equals(this.id, questionBase.id) &&
        Objects.equals(this.name, questionBase.name) &&
        Objects.equals(this.author, questionBase.author) &&
        Objects.equals(this.publishTime, questionBase.publishTime) &&
        Objects.equals(this.content, questionBase.content)&&
            Objects.equals(this.focus, questionBase.focus)&&
            Objects.equals(this.topicId,questionBase.topicId)&&
            Objects.equals(this.viewCount, questionBase.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, author, publishTime, content,focus,viewCount,topicId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionBase {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    focus: ").append(toIndentedString(focus)).append("\n");
    sb.append("    viewCount: ").append(toIndentedString(viewCount)).append("\n");
    sb.append("    TopicId: ").append(toIndentedString(topicId)).append("\n");
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


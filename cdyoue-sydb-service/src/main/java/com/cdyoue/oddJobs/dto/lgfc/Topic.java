package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Topic
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class Topic   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("follow")
  private Integer follow = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("info")
  private String info=null;

  @JsonProperty("coverImg")
  private String coverImg = null;

  @JsonProperty("questionCounts")
  private Integer questionCounts = null;

  @JsonProperty("replyCounts")
  private Integer replyCounts = null;

  @JsonProperty("createBy")
  private String createBy = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  @JsonProperty("questionMinis")
  private Set<QuestionMini> questionMinis ;

  public Topic id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Topic name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 话题名称
   * @return name
  **/
  @ApiModelProperty(example = "双创政策话题", value = "话题名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Topic follow(Integer follow) {
    this.follow = follow;
    return this;
  }

   /**
   * 关注
   * @return follow
  **/
  @ApiModelProperty(example = "1234212", value = "关注")
  public Integer getFollow() {
    return follow;
  }

  public void setFollow(Integer follow) {
    this.follow = follow;
  }

  public Topic createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * 创建时间
   * @return createTime
  **/
  @ApiModelProperty(example = "2017/03/03 12:00:00", value = "创建时间")
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public Topic coverImg(String coverImg) {
    this.coverImg = coverImg;
    return this;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }


  /**
   * 封面图片
   * @return coverImg
  **/
  @ApiModelProperty(example = "/image/pic.jpg", value = "封面图片")
  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Topic topic = (Topic) o;
    return Objects.equals(this.id, topic.id) &&
        Objects.equals(this.name, topic.name) &&
        Objects.equals(this.follow, topic.follow) &&
        Objects.equals(this.createTime, topic.createTime) &&
        Objects.equals(this.coverImg, topic.coverImg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, follow, createTime, coverImg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Topic {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    follow: ").append(toIndentedString(follow)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    coverImg: ").append(toIndentedString(coverImg)).append("\n");
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
    return o.toString().replace("\n", "\n");

  }

  public Set<QuestionMini> getQuestionMinis() {
    return questionMinis;
  }

  public void setQuestionMinis(Set<QuestionMini> questionMinis) {
    this.questionMinis = questionMinis;
  }

  @ApiModelProperty(example = "2017/05/06 15:00:00", value = "获取话题下所有问题的数量")
  public Integer getQuestionCounts() {
    return questionCounts;
  }

  public void setQuestionCounts(Integer questionCounts) {
    this.questionCounts = questionCounts;
  }

    @ApiModelProperty(example = "2017/03/03 15:00:00", value = "获取话题下所有回答数")
  public Integer getReplyCounts() {
    return replyCounts;
  }

  public void setReplyCounts(Integer replyCounts) {
    this.replyCounts = replyCounts;
  }

  @ApiModelProperty( value = "创建人名字")
  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  @ApiModelProperty( value = "话题浏览量")

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }
}


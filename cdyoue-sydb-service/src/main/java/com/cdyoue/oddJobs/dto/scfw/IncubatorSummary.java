package com.cdyoue.oddJobs.dto.scfw;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * IncubatorSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T14:01:12.120Z")

public class IncubatorSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("publishPeople")
  private String publishPeople = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  public IncubatorSummary id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * 孵化园 id 自动生成
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public IncubatorSummary name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 名称
   * @return name
  **/
  @ApiModelProperty(example = "贝壳菁汇孵化器", value = "名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IncubatorSummary publishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
    return this;
  }

   /**
   * 发布人 自动生成
   * @return publishPeople
  **/
  @ApiModelProperty(example = "刘杰", value = "发布人")
  public String getPublishPeople() {
    return publishPeople;
  }

  public void setPublishPeople(String publishPeople) {
    this.publishPeople = publishPeople;
  }

  public IncubatorSummary createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * 创建时间 自动生成
   * @return createTime
  **/
  @ApiModelProperty(example = "2017/4/19 12:34", value = "创建时间")
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public IncubatorSummary viewCount(Integer viewCount) {
    this.viewCount = viewCount;
    return this;
  }

   /**
   * 浏览次数 自动生成
   * @return viewCount
  **/
  @ApiModelProperty(example = "10000", value = "浏览次数")
  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IncubatorSummary incubatorSummary = (IncubatorSummary) o;
    return Objects.equals(this.id, incubatorSummary.id) &&
        Objects.equals(this.name, incubatorSummary.name) &&
        Objects.equals(this.publishPeople, incubatorSummary.publishPeople) &&
        Objects.equals(this.createTime, incubatorSummary.createTime) &&
        Objects.equals(this.viewCount, incubatorSummary.viewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, publishPeople, createTime, viewCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IncubatorSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    publishPeople: ").append(toIndentedString(publishPeople)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    viewCount: ").append(toIndentedString(viewCount)).append("\n");
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


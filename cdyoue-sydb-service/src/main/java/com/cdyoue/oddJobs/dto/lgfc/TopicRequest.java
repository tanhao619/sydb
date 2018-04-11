package com.cdyoue.oddJobs.dto.lgfc;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * TopicRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class TopicRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("coverImg")
  private String coverImg = null;
  
  @JsonProperty("quesionIds")
  private List<Integer> quesionIds = null;

  public TopicRequest name(String name) {
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

  public TopicRequest coverImg(String coverImg) {
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
    TopicRequest topicRequest = (TopicRequest) o;
    return Objects.equals(this.name, topicRequest.name) &&
        Objects.equals(this.coverImg, topicRequest.coverImg);
  }

  @Override
	public int hashCode() {
		return Objects.hash(name, coverImg);
	}

	public List<Integer> getQuesionIds() {
		return quesionIds;
	}

	@ApiModelProperty(value = "关联的问题id列表")
	public void setQuesionIds(List<Integer> quesionIds) {
		this.quesionIds = quesionIds;
	}

@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
    return o.toString().replace("\n", "\n    ");
  }
}


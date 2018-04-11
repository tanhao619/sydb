package com.cdyoue.oddJobs.dto.zscq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * IndustryReportDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-20T03:33:06.490Z")

public class IndustryReportDetail   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("viewCount")
  private Integer viewCount;

  @JsonProperty("publishTime")
  private String publishTime;

  @JsonProperty("coverImgUrl")
  private String coverImgUrl = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("content")
  private String content = null;

  public IndustryReportDetail name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 标题名称
   * @return name
  **/
  @ApiModelProperty(example = "卡拉卡", value = "标题名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IndustryReportDetail coverImgUrl(String coverImgUrl) {
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

  public IndustryReportDetail introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 简介
   * @return introduction
  **/
  @ApiModelProperty(example = "个人简介个人简介个人简介个人简介", value = "简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public IndustryReportDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 项目详细信息(富文本)
   * @return content
  **/
  @ApiModelProperty(example = "项目详细", value = "项目详细信息(富文本)")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @ApiModelProperty(example = "55", value = "浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

  public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

  @ApiModelProperty(example = "2015-02-12 12:22:33", value = "发布时间")
    public String getPublishTime() {
        return publishTime;
    }

  public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndustryReportDetail that = (IndustryReportDetail) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (coverImgUrl != null ? !coverImgUrl.equals(that.coverImgUrl) : that.coverImgUrl != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (coverImgUrl != null ? coverImgUrl.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IndustryReportDetail{" +
                "name='" + name + '\'' +
                ", viewCount=" + viewCount +
                ", publishTime='" + publishTime + '\'' +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}


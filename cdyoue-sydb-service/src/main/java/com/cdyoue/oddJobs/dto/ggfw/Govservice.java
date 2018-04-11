package com.cdyoue.oddJobs.dto.ggfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * Govservice
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class Govservice   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("logoUrl")
  private String logoUrl = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("viewscount")
  private Integer viewscount = null;

  @JsonProperty("link")
    private String link = null;

  @JsonProperty("createTime")
   private String createTime = null;
  public Govservice id(Integer id) {
    this.id = id;
    return this;
  }

  @JsonProperty("createBy")
  private String createBy = null;

   /**
   * id
   * @return id
  **/
  @ApiModelProperty(value = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Govservice title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 申报标题
   * @return title
  **/
  @ApiModelProperty(example = "关于申请2016年度海淀园企业人才公租房租金补贴的通知", value = "申报标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Govservice logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

   /**
   * 附件地址
   * @return logoUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "附件地址")
  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public Govservice introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 简介内容
   * @return introduction
  **/
  @ApiModelProperty(example = "简介内容", value = "简介内容")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public Govservice viewscount(Integer viewscount) {
    this.viewscount = viewscount;
    return this;
  }

   /**
   * 阅读量（后台专用）
   * @return viewscount
  **/
  @ApiModelProperty(example = "10000", value = "阅读量（后台专用）")
  public Integer getViewscount() {
    return viewscount == null ? 0 : viewscount;
  }

  public void setViewscount(Integer viewscount) {
    this.viewscount = viewscount;
  }

  public Govservice link(String link) {
    this.link = link;
    return this;
  }

   /**
   * 官网链接
   * @return link
  **/
  @ApiModelProperty(example = "http://www.youedata.cn/", value = "官网链接")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @ApiModelProperty(example = "2017-07-27 18:22:18", value = "发布时间")
  public String getCreateTime() {
        return createTime;
    }

  public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

  @ApiModelProperty(example = "tangrui", value = "发布人")
  public String getCreateBy() {
        return createBy;
  }

  public void setCreateBy(String createBy) {
        this.createBy = createBy;
  }

    @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Govservice govservice = (Govservice) o;
    return Objects.equals(this.id, govservice.id) &&
        Objects.equals(this.title, govservice.title) &&
        Objects.equals(this.logoUrl, govservice.logoUrl) &&
        Objects.equals(this.introduction, govservice.introduction) &&
        Objects.equals(this.viewscount, govservice.viewscount) &&
        Objects.equals(this.link, govservice.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, logoUrl, introduction, viewscount, link);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Govservice {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    viewscount: ").append(toIndentedString(viewscount)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
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


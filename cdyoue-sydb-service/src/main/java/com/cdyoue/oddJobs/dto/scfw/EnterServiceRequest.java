package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * EnterServiceRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class EnterServiceRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("serviceContent")
  private String serviceContent = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("logoUrl")
  private String logoUrl = null;

  @JsonProperty("contactNo")
  private String contactNo = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  public EnterServiceRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 申报标题
   * @return name
  **/
  @ApiModelProperty(example = "关于申请2016年度海淀园企业人才公租房租金补贴的通知", value = "申报标题")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EnterServiceRequest serviceContent(String serviceContent) {
    this.serviceContent = serviceContent;
    return this;
  }

   /**
   * 服务内容
   * @return serviceContent
  **/
  @ApiModelProperty(example = "金三优服", value = "服务内容")
  public String getServiceContent() {
    return serviceContent;
  }

  public void setServiceContent(String serviceContent) {
    this.serviceContent = serviceContent;
  }

  public EnterServiceRequest introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 简介
   * @return introduction
  **/
  @ApiModelProperty(example = "金三优服,隶属于北京金三科技股份有限公司,专注提供电信增值业务许可证,icp经营许可证,edi许可证,", value = "简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public EnterServiceRequest logoUrl(String logoUrl) {
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

  public EnterServiceRequest contactNo(String contactNo) {
    this.contactNo = contactNo;
    return this;
  }

   /**
   * 联系电话
   * @return contactNo
  **/
  @ApiModelProperty(example = "1380000000", value = "联系电话")
  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public EnterServiceRequest content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 详情
   * @return content
  **/
  @ApiModelProperty(example = "FN123344H", value = "详情")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public EnterServiceRequest viewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
    return this;
  }

   /**
   * 阅读量
   * @return viewsCount
  **/
  @ApiModelProperty(example = "10000", value = "阅读量")
  public Integer getViewsCount() {
    return viewsCount == null ? 0 : viewsCount;
  }

  public void setViewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterServiceRequest enterServiceRequest = (EnterServiceRequest) o;
    return Objects.equals(this.name, enterServiceRequest.name) &&
        Objects.equals(this.serviceContent, enterServiceRequest.serviceContent) &&
        Objects.equals(this.introduction, enterServiceRequest.introduction) &&
        Objects.equals(this.logoUrl, enterServiceRequest.logoUrl) &&
        Objects.equals(this.contactNo, enterServiceRequest.contactNo) &&
        Objects.equals(this.content, enterServiceRequest.content) &&
        Objects.equals(this.viewsCount, enterServiceRequest.viewsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, serviceContent, introduction, logoUrl, contactNo, content, viewsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnterServiceRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    serviceContent: ").append(toIndentedString(serviceContent)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    contactNo: ").append(toIndentedString(contactNo)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    viewsCount: ").append(toIndentedString(viewsCount)).append("\n");
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


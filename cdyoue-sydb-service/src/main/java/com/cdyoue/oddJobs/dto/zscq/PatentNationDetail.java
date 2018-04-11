package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * PatentNationDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

public class PatentNationDetail   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("ipc")
  private String ipc = null;

  @JsonProperty("publicNO")
  private String publicNO = null;

  @JsonProperty("publicDate")
  private String publicDate = null;

  @JsonProperty("applicationNO")
  private String applicationNO = null;

  @JsonProperty("applicationDate")
  private String applicationDate = null;

  @JsonProperty("patentType")
  private Integer patentType = null;

  @JsonProperty("applicant")
  private String applicant = null;

  @JsonProperty("inventor")
  private String inventor = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  public PatentNationDetail name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 专利名称
   * @return name
  **/
  @ApiModelProperty(example = "一种聚义", value = "专利名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PatentNationDetail ipc(String ipc) {
    this.ipc = ipc;
    return this;
  }

   /**
   * IPC分类号
   * @return ipc
  **/
  @ApiModelProperty(example = "G06F17/50", value = "IPC分类号")
  public String getIpc() {
    return ipc;
  }

  public void setIpc(String ipc) {
    this.ipc = ipc;
  }

  public PatentNationDetail publicNO(String publicNO) {
    this.publicNO = publicNO;
    return this;
  }

   /**
   * 公开（公告）号
   * @return publicNO
  **/
  @ApiModelProperty(example = "CN1234567890", value = "公开（公告）号")
  public String getPublicNO() {
    return publicNO;
  }

  public void setPublicNO(String publicNO) {
    this.publicNO = publicNO;
  }

  public PatentNationDetail publicDate(String publicDate) {
    this.publicDate = publicDate;
    return this;
  }

   /**
   * 公开（公告）日
   * @return publicDate
  **/
  @ApiModelProperty(example = "2017/03/03", value = "公开（公告）日")
  public String getPublicDate() {
    return publicDate;
  }

  public void setPublicDate(String publicDate) {
    this.publicDate = publicDate;
  }

  public PatentNationDetail applicationNO(String applicationNO) {
    this.applicationNO = applicationNO;
    return this;
  }

   /**
   * 申请号
   * @return applicationNO
  **/
  @ApiModelProperty(example = "CN1234567890", value = "申请号")
  public String getApplicationNO() {
    return applicationNO;
  }

  public void setApplicationNO(String applicationNO) {
    this.applicationNO = applicationNO;
  }

  public PatentNationDetail applicationDate(String applicationDate) {
    this.applicationDate = applicationDate;
    return this;
  }

   /**
   * 申请日
   * @return applicationDate
  **/
  @ApiModelProperty(example = "2017/03/03", value = "申请日")
  public String getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(String applicationDate) {
    this.applicationDate = applicationDate;
  }

  public PatentNationDetail patentType(Integer patentType) {
    this.patentType = patentType;
    return this;
  }

   /**
   * 专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等
   * @return patentType
  **/
  @ApiModelProperty(example = "2", value = "专利类别（专利特有）：1 发明专利，2 实用新型专利，3 外观设计专利等")
  public Integer getPatentType() {
    return patentType;
  }

  public void setPatentType(Integer patentType) {
    this.patentType = patentType;
  }

  public PatentNationDetail applicant(String applicant) {
    this.applicant = applicant;
    return this;
  }

   /**
   * 申请人
   * @return applicant
  **/
  @ApiModelProperty(example = "2017/03/03", value = "申请人")
  public String getApplicant() {
    return applicant;
  }

  public void setApplicant(String applicant) {
    this.applicant = applicant;
  }

  public PatentNationDetail inventor(String inventor) {
    this.inventor = inventor;
    return this;
  }

   /**
   * 发明人
   * @return inventor
  **/
  @ApiModelProperty(example = "李某某", value = "发明人")
  public String getInventor() {
    return inventor;
  }

  public void setInventor(String inventor) {
    this.inventor = inventor;
  }

  public PatentNationDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 详细内容
   * @return content
  **/
  @ApiModelProperty(example = "1996年10月，王强在北京新东方学校开创了称", value = "详细内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public PatentNationDetail viewsCount(Integer viewsCount) {
    this.viewsCount = viewsCount;
    return this;
  }

   /**
   * 浏览量（admin）
   * @return viewsCount
  **/
  @ApiModelProperty(example = "10000", value = "浏览量（admin）")
  public Integer getViewsCount() {
    return viewsCount;
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
    PatentNationDetail patentNationDetail = (PatentNationDetail) o;
    return Objects.equals(this.name, patentNationDetail.name) &&
        Objects.equals(this.ipc, patentNationDetail.ipc) &&
        Objects.equals(this.publicNO, patentNationDetail.publicNO) &&
        Objects.equals(this.publicDate, patentNationDetail.publicDate) &&
        Objects.equals(this.applicationNO, patentNationDetail.applicationNO) &&
        Objects.equals(this.applicationDate, patentNationDetail.applicationDate) &&
        Objects.equals(this.patentType, patentNationDetail.patentType) &&
        Objects.equals(this.applicant, patentNationDetail.applicant) &&
        Objects.equals(this.inventor, patentNationDetail.inventor) &&
        Objects.equals(this.content, patentNationDetail.content) &&
        Objects.equals(this.viewsCount, patentNationDetail.viewsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, ipc, publicNO, publicDate, applicationNO, applicationDate, patentType, applicant, inventor, content, viewsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatentNationDetail {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ipc: ").append(toIndentedString(ipc)).append("\n");
    sb.append("    publicNO: ").append(toIndentedString(publicNO)).append("\n");
    sb.append("    publicDate: ").append(toIndentedString(publicDate)).append("\n");
    sb.append("    applicationNO: ").append(toIndentedString(applicationNO)).append("\n");
    sb.append("    applicationDate: ").append(toIndentedString(applicationDate)).append("\n");
    sb.append("    patentType: ").append(toIndentedString(patentType)).append("\n");
    sb.append("    applicant: ").append(toIndentedString(applicant)).append("\n");
    sb.append("    inventor: ").append(toIndentedString(inventor)).append("\n");
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


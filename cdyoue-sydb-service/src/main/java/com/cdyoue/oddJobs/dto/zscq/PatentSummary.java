package com.cdyoue.oddJobs.dto.zscq;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * PatentSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:34:36.300Z")

public class PatentSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("applicationNO")
  private String applicationNO = null;

  @JsonProperty("publicNO")
  private String publicNO = null;

  @JsonProperty("applicationDate")
  private String applicationDate = null;

  @JsonProperty("inventor")
  private String inventor = null;

  @JsonProperty("patentType")
  private Integer patentType = null;

  @JsonProperty("viewsCount")
  private Integer viewsCount = null;

  public PatentSummary id(Integer id) {
    this.id = id;
    return this;
  }

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

  public PatentSummary name(String name) {
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

  public PatentSummary applicationNO(String applicationNO) {
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

  public PatentSummary publicNO(String publicNO) {
    this.publicNO = publicNO;
    return this;
  }

   /**
   * 公开公告号
   * @return publicNO
  **/
  @ApiModelProperty(example = "CN1234567890", value = "公开公告号")
  public String getPublicNO() {
    return publicNO;
  }

  public void setPublicNO(String publicNO) {
    this.publicNO = publicNO;
  }

  public PatentSummary applicationDate(String applicationDate) {
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

  public PatentSummary inventor(String inventor) {
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

  public PatentSummary patentType(Integer patentType) {
    this.patentType = patentType;
    return this;
  }

   /**
   * 专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等
   * @return patentType
  **/
  @ApiModelProperty(example = "2", value = "专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等")
  public Integer getPatentType() {
    return patentType;
  }

  public void setPatentType(Integer patentType) {
    this.patentType = patentType;
  }

  public PatentSummary viewsCount(Integer viewsCount) {
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
    PatentSummary patentSummary = (PatentSummary) o;
    return Objects.equals(this.id, patentSummary.id) &&
        Objects.equals(this.name, patentSummary.name) &&
        Objects.equals(this.applicationNO, patentSummary.applicationNO) &&
        Objects.equals(this.publicNO, patentSummary.publicNO) &&
        Objects.equals(this.applicationDate, patentSummary.applicationDate) &&
        Objects.equals(this.inventor, patentSummary.inventor) &&
        Objects.equals(this.patentType, patentSummary.patentType) &&
        Objects.equals(this.viewsCount, patentSummary.viewsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, applicationNO, publicNO, applicationDate, inventor, patentType, viewsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatentSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    applicationNO: ").append(toIndentedString(applicationNO)).append("\n");
    sb.append("    publicNO: ").append(toIndentedString(publicNO)).append("\n");
    sb.append("    applicationDate: ").append(toIndentedString(applicationDate)).append("\n");
    sb.append("    inventor: ").append(toIndentedString(inventor)).append("\n");
    sb.append("    patentType: ").append(toIndentedString(patentType)).append("\n");
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


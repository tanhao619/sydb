package com.cdyoue.oddJobs.dto.ggfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * Govproject
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-15T01:26:16.185Z")

public class Govproject   {
  @JsonProperty("govprojectSummary")
  private GovprojectSummary govprojectSummary = null;

  @JsonProperty("inforSource")
  private String inforSource = null;

  @JsonProperty("fileNO")
  private String fileNO = null;

  @JsonProperty("introduction")
  private String introduction = null;

  public Govproject govprojectSummary(GovprojectSummary govprojectSummary) {
    this.govprojectSummary = govprojectSummary;
    return this;
  }

   /**
   * Get govproject
   * @return govproject
  **/
  @ApiModelProperty(value = "")
  public GovprojectSummary getGovprojectSummary() {
    return govprojectSummary;
  }

  public void setGovprojectSummary(GovprojectSummary govprojectSummary) {
    this.govprojectSummary = govprojectSummary;
  }

  public Govproject inforSource(String inforSource) {
    this.inforSource = inforSource;
    return this;
  }

   /**
   * 信息来源
   * @return inforSource
  **/
  @ApiModelProperty(example = "国家统计局", value = "信息来源")
  public String getInforSource() {
    return inforSource;
  }

  public void setInforSource(String inforSource) {
    this.inforSource = inforSource;
  }

  public Govproject introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

    /**
     * 详细介绍
     * @return introduction
     **/
    @ApiModelProperty(example = "国家统计局", value = "信息来源")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

   /**
   * 申报文件号
   * @return fileNO
  **/
  @ApiModelProperty(example = "FN123344H", value = "申报文件号")
  public String getFileNO() {
    return fileNO;
  }

  public void setFileNO(String fileNO) {
    this.fileNO = fileNO;
  }

  public Govproject fileNO(String fileNO) {
        this.fileNO = fileNO;
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
    Govproject govproject = (Govproject) o;
    return Objects.equals(this.govprojectSummary, govproject.govprojectSummary) &&
        Objects.equals(this.inforSource, govproject.inforSource) &&
        Objects.equals(this.fileNO, govproject.fileNO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(govprojectSummary, inforSource, fileNO);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Govproject {\n");
    
    sb.append("    govproject: ").append(toIndentedString(govprojectSummary)).append("\n");
    sb.append("    inforSource: ").append(toIndentedString(inforSource)).append("\n");
    sb.append("    fileNO: ").append(toIndentedString(fileNO)).append("\n");
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


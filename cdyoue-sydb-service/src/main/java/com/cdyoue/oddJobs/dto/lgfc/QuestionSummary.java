package com.cdyoue.oddJobs.dto.lgfc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * QuestionSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class QuestionSummary   {
  @JsonProperty("questionBase")
  private QuestionBase questionBase = null;

  @JsonProperty("followNum")
  private Integer followNum = null;

  @JsonProperty("replyNum")
  private Integer replyNum = null;

  @JsonProperty("certs")//认证信息
  private List<Integer> certs = new ArrayList<Integer>();

  public QuestionSummary questionBase(QuestionBase questionBase) {
    this.questionBase = questionBase;
    return this;
  }

   /**
   * Get questionBase
   * @return questionBase
  **/
  @ApiModelProperty(value = "")
  public QuestionBase getQuestionBase() {
    return questionBase;
  }

  public void setQuestionBase(QuestionBase questionBase) {
    this.questionBase = questionBase;
  }

  public QuestionSummary followNum(Integer followNum) {
    this.followNum = followNum;
    return this;
  }

   /**
   * 关注数
   * @return followNum
  **/
  @ApiModelProperty(example = "123", value = "关注数")
  public Integer getFollowNum() {
    return followNum;
  }

  public void setFollowNum(Integer followNum) {
    this.followNum = followNum;
  }

  public QuestionSummary replyNum(Integer replyNum) {
    this.replyNum = replyNum;
    return this;
  }

   /**
   * 回答数
   * @return replyNum
  **/
  @ApiModelProperty(example = "100", value = "回答数")
  public Integer getReplyNum() {
    return replyNum;
  }

  public void setReplyNum(Integer replyNum) {
    this.replyNum = replyNum;
  }

  /**
   * 认证信息
   * @return
   */
  @ApiModelProperty(example = "1", value = "认证类型：1实名，2大咖，3导师")
  public List<Integer> getCerts() {
    return certs;
  }

  public void setCerts(List<Integer> certs) {
    this.certs = certs;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionSummary questionSummary = (QuestionSummary) o;
    return Objects.equals(this.questionBase, questionSummary.questionBase) &&
        Objects.equals(this.followNum, questionSummary.followNum) &&
        Objects.equals(this.replyNum, questionSummary.replyNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionBase, followNum, replyNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionSummary {\n");
    
    sb.append("    questionBase: ").append(toIndentedString(questionBase)).append("\n");
    sb.append("    followNum: ").append(toIndentedString(followNum)).append("\n");
    sb.append("    replyNum: ").append(toIndentedString(replyNum)).append("\n");
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


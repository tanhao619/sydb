package com.cdyoue.oddJobs.dto.lgfc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * QuesionDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class QuestionDetail   {
  @JsonProperty("questionBase")
  private QuestionBase questionBase = null;

  @JsonProperty("replies")
  private List<ReplyDetails> replies = new ArrayList<ReplyDetails>();

  public QuestionDetail questionBase(QuestionBase questionBase) {
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

  public QuestionDetail replies(List<ReplyDetails> replies) {
    this.replies = replies;
    return this;
  }

  public QuestionDetail addRepliesItem(ReplyDetails repliesItem) {
    this.replies.add(repliesItem);
    return this;
  }

   /**
   * 回复详情信息
   * @return replies
  **/
  @ApiModelProperty(value = "回复详情信息")
  public List<ReplyDetails> getReplies() {
    return replies;
  }

  public void setReplies(List<ReplyDetails> replies) {
    this.replies = replies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionDetail quesionDetail = (QuestionDetail) o;
    return Objects.equals(this.questionBase, quesionDetail.questionBase) &&
        Objects.equals(this.replies, quesionDetail.replies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionBase, replies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuesionDetail {\n");
    
    sb.append("    questionBase: ").append(toIndentedString(questionBase)).append("\n");
    sb.append("    replies: ").append(toIndentedString(replies)).append("\n");
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


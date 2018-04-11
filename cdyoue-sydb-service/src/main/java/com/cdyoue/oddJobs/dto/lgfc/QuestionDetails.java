package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by sky on 2017/5/6.
 */
public class QuestionDetails {
    @JsonProperty("replyNum")
    private Integer replyNum;
    @JsonProperty("focusNum")
    private Integer focusNum;

    @JsonProperty("questionBase")
    private QuestionBase questionBase = null;

    public QuestionDetails questionBase(QuestionBase questionBase) {
        this.questionBase = questionBase;
        return this;
    }

    /**
     * Get questionBase
     * @return questionBase
     **/
    @ApiModelProperty(value = "问题基本信息")
    public QuestionBase getQuestionBase() {
        return questionBase;
    }

    public void setQuestionBase(QuestionBase questionBase) {
        this.questionBase = questionBase;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionDetails quesionDetails = (QuestionDetails) o;
        return Objects.equals(this.questionBase, quesionDetails.questionBase) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionBase);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuesionDetails {\n");

        sb.append("    questionBase: ").append(toIndentedString(questionBase)).append("\n");
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

    @ApiModelProperty(value = "回复数")
    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }
    @ApiModelProperty(value = "关注数")
    public Integer getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(Integer focusNum) {
        this.focusNum = focusNum;
    }
}

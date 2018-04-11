package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengshaojun on 2017/5/3.
 * QuestionUserReply
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class QuestionUserReply {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("replies")
    private List<Reply4User> replies = new ArrayList<Reply4User>();


    public QuestionUserReply name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 问题名称
     * @return name
     **/
    @ApiModelProperty(example = "怎样进行团队建设？", value = "问题名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public QuestionUserReply content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 问题内容
     * @return content
     **/
    @ApiModelProperty(example = "除了欧文，还有谁向科比发出过这样的挑战", value = "问题内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public QuestionUserReply replies(List<Reply4User> replies) {
        this.replies = replies;
        return this;
    }

    public QuestionUserReply addRepliesItem(Reply4User repliesItem) {
        this.replies.add(repliesItem);
        return this;
    }

    /**
     * 用户的回复信息
     * @return replies
     **/
    @ApiModelProperty(value = "用户的回复信息")
    public List<Reply4User> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply4User> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionUserReply that = (QuestionUserReply) o;

        if (!name.equals(that.name)) return false;
        if (!content.equals(that.content)) return false;
        if (!replies.equals(that.replies)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + replies.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionUserReply {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

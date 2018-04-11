package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by dengshaojun on 2017/5/3.
 * Reply4User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")

public class Reply4User {


    @JsonProperty("context")
    private String context = null;

    @JsonProperty("createTime")
    private String createTime = null;


    public Reply4User context(String context) {
        this.context = context;
        return this;
    }

    /**
     * 回复内容
     * @return content
     **/
    @ApiModelProperty(example = "天下武功，唯快不破！", value = "回复内容")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public Reply4User createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 回复时间
     * @return createTime
     **/
    @ApiModelProperty(example = "2017-05-03", value = "回复时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }



    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reply4User reply4User = (Reply4User) o;
        return Objects.equals(this.context, reply4User.context) &&
                Objects.equals(this.createTime, reply4User.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, createTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Reply4User {\n");

        sb.append("    context: ").append(toIndentedString(context)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
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

package com.cdyoue.oddJobs.dto.lgfc;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by dengshaojun on 2017/5/3.
 * Reply4Question
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T14:30:56.295Z")
public class Reply4Question {

    @JsonProperty("reply")
    private String reply;

    /**
     * 回复内容
     * @return content
     **/
    @ApiModelProperty(example = "天下武功，唯快不破！", value = "回复内容")
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}

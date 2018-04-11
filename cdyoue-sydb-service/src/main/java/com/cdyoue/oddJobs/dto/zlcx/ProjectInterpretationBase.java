package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/28.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectInterpretationBase {
    @JsonProperty("title")
    private String title = null;

    @JsonProperty("content")
    private String content = null;

    /**
     * 项目解读名称
     * @return
     **/
    @ApiModelProperty(example = "项目解读1", value = "项目解读名称")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }/**
     * 项目解读内容
     * @return
     **/
    @ApiModelProperty(example = "这是项目解读的内容", value = "项目解读内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

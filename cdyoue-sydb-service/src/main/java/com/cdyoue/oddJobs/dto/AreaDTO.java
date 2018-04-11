package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/5/11.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")
public class AreaDTO {
    @JsonProperty("fristArea")
    private String fristArea;

    @JsonProperty("secondArea")
    private List<String> secondArea;


    /**
     * 名称或者标题
     * @return fristArea
     **/
    @ApiModelProperty(example = "四川省", value = "一级地区")
    public String getFristArea() {
        return fristArea;
    }

    public void setFristArea(String fristArea) {
        this.fristArea = fristArea;
    }

    /**
     * 名称或者标题
     * @return fristArea
     **/
    @ApiModelProperty(example = "成都", value = "二级地区")
    public List<String> getSecondArea() {
        return secondArea;
    }

    public void setSecondArea(List<String> secondArea) {
        this.secondArea = secondArea;
    }

}

package com.cdyoue.oddJobs.dto.algorithm;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by liaoyoule on 2017/6/14.
 */
public class AlgorithmTypeRequest {
    private String name;
    private String intro;


    @ApiModelProperty(value = "名称")
    @NotNull
    @Size(min = 1,message = "名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ApiModelProperty(value = "简介")

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}

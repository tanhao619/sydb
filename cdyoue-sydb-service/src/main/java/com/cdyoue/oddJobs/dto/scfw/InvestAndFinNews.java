package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tr on 2017/6/7.
 */
public class InvestAndFinNews {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("infoAndDept")
    private String infoAndDept;

    @ApiModelProperty(example = "简介 or 摩根大通", value = "融资项目简介 OR 投资人机构")
    public String getInfoAndDept() {
        return infoAndDept;
    }

    public void setInfoAndDept(String infoAndDept) {
        this.infoAndDept = infoAndDept;
    }

    @ApiModelProperty(example = "投资人名 或 项目名", value = "投资人名或融资项目名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "1", value = "1：融资 2：投资")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}

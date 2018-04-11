package com.cdyoue.oddJobs.dto.zlcy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/10/12.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class TrainingCreate {
    @JsonProperty("name")
    private String name=null;

    @JsonProperty("info")
    private String info=null;

    @JsonProperty("logo")
    private String logo=null;

    @JsonProperty("content")
    private String content=null;

    @JsonProperty("industry")
    private String industry=null;

    @JsonProperty("address")
    private String address=null;

    /**
     * 培训机构名称
     * @return
     **/
    @ApiModelProperty(example = "卡拉卡",value = "培训机构名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 培训机构简介
     * @return
     **/
    @ApiModelProperty(example = "卡拉卡简介",value = "培训机构简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * 培训机构logo
     * @return id
     **/
    @ApiModelProperty(example = "sy/10-11/b8a33f15-2d6c-4773-b299-5610a5889a8d.jpg",value = "培训机构logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    /**
     * 培训机构详情
     * @return id
     **/
    @ApiModelProperty(example = "卡拉卡详情",value = "培训机构详情")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 培训机构行业
     * @return id
     **/
    @ApiModelProperty(example = "计算机",value = "培训机构行业")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    /**
     * 培训机构地址
     * @return id
     **/
    @ApiModelProperty(example = "{'province':'北京市','city':'东城区','address':'东街28号'}",value = "培训机构地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
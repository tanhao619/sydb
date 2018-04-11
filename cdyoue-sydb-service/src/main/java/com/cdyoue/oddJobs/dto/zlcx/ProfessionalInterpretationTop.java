package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专业解读top
 */
public class ProfessionalInterpretationTop {

    private Integer id;
    private String topImgUrl;
    private String name;
    private String introduction;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
    public String getTopImgUrl() {
        return topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    @ApiModelProperty(example = "专业解读内容名称", value = "专业解读内容名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "专业解读内容简介专业解读内容简介专业解读内容简介", value = "专业解读内容简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

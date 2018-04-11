package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-职业经历列表
 */
public class ExpertCareer {

    private Integer id;
    private String brief;
    private String detail;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "2016.03至2016.03 国信优易数据有限公司·市场总监", value = "yyyy.MM至yyyy.MM 公司·职位")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(example = "1，根据产品需求文档设计公司app及网页的原型稿，并利用Auxre做交互。", value = "经历详情")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

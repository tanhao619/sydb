package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家职业经历表单
 */
public class ExpertCareerRequest {

    private Integer id;
    private String brief;
    private String detail;

    @ApiModelProperty(example = "", value = "新增时：无id；编辑时：现有经历加id，新加经历无id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*@NotBlank(message = "经历不能为空")*/
    @ApiModelProperty(example = "2016.03至2016.03 国信优易数据有限公司·市场总监", value = "yyyy.MM至yyyy.MM 公司·职位")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    /*@NotBlank(message = "详细不能为空")*/
    @ApiModelProperty(example = "1，根据产品需求文档设计公司app及网页的原型稿，并利用Auxre做交互。", value = "经历详情")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

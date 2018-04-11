package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 行业分类
 */
public class IndustryType {

    private Integer id;
    private String name;

    @ApiModelProperty(example = "1", value = "行业分类主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "通用设备制造业", value = "行业分类名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

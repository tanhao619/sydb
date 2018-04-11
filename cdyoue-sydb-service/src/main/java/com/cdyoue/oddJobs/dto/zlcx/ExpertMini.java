package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家下拉列表
 */
public class ExpertMini {

    private Integer id;
    private String name;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "专家名称", value = "专家名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

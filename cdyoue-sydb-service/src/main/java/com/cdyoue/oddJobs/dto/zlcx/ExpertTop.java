package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家成果top
 */
public class ExpertTop {

    private Integer id;
    private String topImgUrl;
    private String name;
    private String job;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "sy/172.16.0.102/image.jpg", value = "置顶图片链接")
    public String getTopImgUrl() {
        return topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    @ApiModelProperty(example = "专家名称", value = "专家名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "职位信息", value = "职位信息")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

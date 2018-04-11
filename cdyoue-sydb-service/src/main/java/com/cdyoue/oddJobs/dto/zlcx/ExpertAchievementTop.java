package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家成果top
 */
public class ExpertAchievementTop {

    private Integer id;
    private String topImgUrl;
    private String name;
    private Long createTime;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "sy/10-25/image.jpg", value = "置顶图片链接")
    public String getTopImgUrl() {
        return topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    @ApiModelProperty(example = "成果名称成果名称成果名称", value = "成果名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "2017-08-09", value = "发布时间")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}

package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专业解读列表
 */
public class ProfessionalInterpretationSummary extends ProfessionalInterpretationTop {


    private String author;
    private String publisher;
    private Long createTime;
    private Integer viewCount;
    private Integer collectCount;
    private Byte top;

    @ApiModelProperty(example = "专家名称", value = "作者")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @ApiModelProperty(example = "管理员001", value = "发布人（后台）")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ApiModelProperty(example = "2017-06-06 12:34", value = "创建时间（后台）")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "2", value = "浏览量（后台）")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "2", value = "收藏量（后台）")
    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    @ApiModelProperty(example = "0", value = "置顶（后台）0：置顶操作；1：取消置顶操作")
    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }

}

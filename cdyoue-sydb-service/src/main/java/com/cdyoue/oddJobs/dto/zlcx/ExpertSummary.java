package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家列表
 */
public class ExpertSummary extends ExpertTop {

    private String headImgUrl;
    private String introduction;
    private String publisher;
    private Long createTime;
    private Integer viewCount;
    private Integer contactCount;
    private Byte top;

    @ApiModelProperty(example = "sy/10-28/sdsada", value = "头像图片链接")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @ApiModelProperty(example = "专家简介专家简介专家简介专家。。。", value = "专家简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @ApiModelProperty(example = "管理员002", value = "发布人（后台）")
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

    @ApiModelProperty(example = "324", value = "浏览次数（后台）")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "23", value = "联系次数（后台）")
    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    @ApiModelProperty(example = "1", value = "置顶（后台）0：置顶操作；1：取消置顶操作")
    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }
}

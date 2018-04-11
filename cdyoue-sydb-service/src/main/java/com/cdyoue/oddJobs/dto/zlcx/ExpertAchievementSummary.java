package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家成果列表
 */
public class ExpertAchievementSummary extends ExpertAchievementTop {

    private String coverImgUrl;
    private String introduction;
    private String expertName;
    private String publisher;
    private Integer viewCount;
    private Integer collectCount;
    private Byte top;

    @ApiModelProperty(example = "sy/10-25/image.jpg", value = "封面图片链接")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @ApiModelProperty(example = "成果简介成果简介成果简介成果简介成果简介成果简介成果简介成果简介成果简介", value = "简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @ApiModelProperty(example = "专家名", value = "专家名（后台）")
    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    @ApiModelProperty(example = "管理员001", value = "发布人（后台）")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ApiModelProperty(example = "32", value = "浏览量（后台）")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "32", value = "收藏量（后台）")
    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    @ApiModelProperty(example = "0", value = "置顶（后台）:0不置顶 1是置顶")
    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }
}

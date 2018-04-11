package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专业解读详情
 */
public class ProfessionalInterpretationDetail {

    private Integer id;
    private String name;
    private String author;
    private Long createTime;
    private Integer viewCount;
    private Integer collectCount;
    private String content;
    private String introduction;
    private String imgUrl;
    private Integer expertId;
    private Boolean collected;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "专业解读名称", value = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(example = "专家名称", value = "作者")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @ApiModelProperty(example = "2017-3-10", value = "发布时间")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "1", value = "浏览量")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "1", value = "收藏量")
    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    @ApiModelProperty(example = "富文本富文本富文本", value = "富文本")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "专业解读内容简介专业解读内容简介专业解读内容简介", value = "专业解读简介（后台）")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @ApiModelProperty(example = "sy/9-30/dsif", value = "封面图片链接（后台）")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @ApiModelProperty(example = "1", value = "所属专家id")
    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    @ApiModelProperty(example = "true", value = "是否收藏T/F")
    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }
}

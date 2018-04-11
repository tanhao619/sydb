package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家成果表单
 */
public class ExpertAchievementRequest {

    private String name;
    private String introduction;
    private String content;
    private String coverImgUrl;
    private Integer expertId;

    /*@NotBlank(message = "成果名称不能为空")*/
    @ApiModelProperty(example = "成果名称", value = "成果名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@NotBlank(message = "成果简介不能为空")*/
    @ApiModelProperty(example = "成果简介", value = "成果简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /*@NotBlank(message = "成果详情不能为空")*/
    @ApiModelProperty(example = "成果详情富文本成果详情富文本成果详情富文本成果详情富文本", value = "成果详情富文本")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*@NotBlank(message = "没有上传图片或上传图片失败")*/
    @ApiModelProperty(example = "http://www.sdyuf.von", value = "封面图片链接")
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @ApiModelProperty(example = "1", value = "所属专家id")
    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }
}

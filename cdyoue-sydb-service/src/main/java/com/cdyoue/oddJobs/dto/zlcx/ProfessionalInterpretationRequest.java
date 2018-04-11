package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专业解读表单
 */
public class ProfessionalInterpretationRequest {

    private String name;
    private String introduction;
    private String content;
    private Integer expertId;

    /*@NotBlank(message = "解读名称不能为空")*/
    @ApiModelProperty(example = "解读名称", value = "解读名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@NotBlank(message = "解读简介不能为空")*/
    @ApiModelProperty(example = "解读简介", value = "解读简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /*@NotBlank(message = "解读详情不能为空")*/
    @ApiModelProperty(example = "解读详情富文本解读详情富文本解读详情富文本解读详情富文本解读详情富文本解读详情富文本", value = "解读详情富文本")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "1", value = "所属专家id")
    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }
}

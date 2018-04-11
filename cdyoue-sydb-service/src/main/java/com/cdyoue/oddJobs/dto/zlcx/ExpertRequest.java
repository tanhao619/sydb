package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 专家表单（含职业经历）
 */
public class ExpertRequest {

    private String name;
    private String headImgUrl;
    private String job;
    private Integer gender;
    private String workYear;
    private Integer industryType;
    private String introduction;
    private String content;
    private List<ExpertCareerRequest> careers;

    /*@NotBlank(message = "姓名不能为空")*/
    @ApiModelProperty(example = "川普", value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@NotBlank(message = "没有上传图片或上传图片失败")*/
    @ApiModelProperty(example = "http", value = "头像链接")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /*@NotBlank(message = "职位不能为空")*/
    @ApiModelProperty(example = "总统", value = "职位")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /*@NotNull(message = "性别不能为空")*/
    @ApiModelProperty(example = "0", value = "性别0：男；1：女")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @ApiModelProperty(example = "22年", value = "工作经验")
    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    /*@NotNull(message = "未选择行业")*/
    @ApiModelProperty(example = "1", value = "行业分类id")
    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    /*@NotBlank(message = "专家简介不能为空")*/
    @ApiModelProperty(example = "逗死一封。。。", value = "一级二级列表页的简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /*@NotBlank(message = "专家详情不能为空")*/
    @ApiModelProperty(example = "个人简介富文本个人简介富文本个人简介富文本个人简介富文本个人简介富文本", value = "个人简介富文本")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*@Valid*/
    @ApiModelProperty(example = "", value = "职位经历列表")
    public List<ExpertCareerRequest> getCareers() {
        return careers;
    }

    public void setCareers(List<ExpertCareerRequest> careers) {
        this.careers = careers;
    }
}

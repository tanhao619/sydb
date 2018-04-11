package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 专家库-专家基本信息
 */
public class ExpertBaseinfo extends ExpertTop {

    private String headImgUrl;
    private Byte gender;
    private Integer age;
    private String homePlace;
    private String livePlace;
    private String workYear;
    private String industry;
    private String introduction;
    private String content;
    private Integer industryType;

    @ApiModelProperty(example = "sy/10-28/sdsada", value = "头像图片链接")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @ApiModelProperty(example = "0", value = "性别（0：男；1：女）")
    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    @ApiModelProperty(example = "13", value = "年龄")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ApiModelProperty(example = "北京·房山", value = "家乡")
    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    @ApiModelProperty(example = "北京·房山", value = "现居地")
    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    @ApiModelProperty(example = "21年", value = "工作经验")
    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    @ApiModelProperty(example = "高新科技", value = "所在行业")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @ApiModelProperty(example = "个人简介", value = "个人简介")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @ApiModelProperty(example = "个人简介详情富文本", value = "个人简介详情富文本")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "1", value = "所在行业id")
    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }
}

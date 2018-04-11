package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 联系专家列表
 */
public class ExpertContactSummary {

    private Integer id;
    private String createEnter;
    private String expertName;
    private Long createTime;
    private String contactPeople;
    private String contactTel;
    private String consultationContent;

    @ApiModelProperty(example = "1", value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "成都京东方", value = "创建企业")
    public String getCreateEnter() {
        return createEnter;
    }

    public void setCreateEnter(String createEnter) {
        this.createEnter = createEnter;
    }

    @ApiModelProperty(example = "习近平", value = "专家名称")
    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    @ApiModelProperty(example = "2017-06-06 12:34", value = "创建时间")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(example = "李克强", value = "联系人")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    @ApiModelProperty(example = "021-82563196", value = "联系电话")
    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    @ApiModelProperty(example = "咨询内容咨询内容咨询内容咨询内容咨询内容咨询内容", value = "咨询内容")
    public String getConsultationContent() {
        return consultationContent;
    }

    public void setConsultationContent(String consultationContent) {
        this.consultationContent = consultationContent;
    }

}

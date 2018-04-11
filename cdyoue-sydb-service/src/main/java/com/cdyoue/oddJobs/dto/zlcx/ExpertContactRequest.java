package com.cdyoue.oddJobs.dto.zlcx;

import io.swagger.annotations.ApiModelProperty;

/**
 * 联系专家表单
 */
public class ExpertContactRequest {

    private String contactPeople;
    private String contactTel;
    private String consultationContent;

    /*@NotBlank(message = "联系人不能为空")*/
    @ApiModelProperty(example = "安倍晋三", value = "联系人")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    /*@NotBlank(message = "联系电话不能为空或格式不对")*/
    @ApiModelProperty(example = "026-85637864", value = "联系电话")
    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /*@NotBlank(message = "咨询内容不能为空")*/
    @ApiModelProperty(example = "通过对高校心理咨询内容和重点的分析，认为随着科技进步和教改的推进，应将发展性咨询作为我国高校心理咨询的重点", value = "咨询内容")
    public String getConsultationContent() {
        return consultationContent;
    }

    public void setConsultationContent(String consultationContent) {
        this.consultationContent = consultationContent;
    }
}

package com.cdyoue.oddJobs.dto.zlcy;

import io.swagger.annotations.ApiModelProperty;

/**
 * 申请入驻基地的企业信息详细
 */
public class ApplicationCheckInDetail {

    private Integer id;
    private String createEnter;
    private String enterpriseLogo;
    private String enterpriseInfo;
    private String enterpriseUrl;
    private String contactPeople;
    private String contactNumber;

    @ApiModelProperty(example = "1",value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(example = "某某",value = "创建企业")
    public String getCreateEnter() {
        return createEnter;
    }

    public void setCreateEnter(String createEnter) {
        this.createEnter = createEnter;
    }

    @ApiModelProperty(example = "1.jpg",value = "企业logo")
    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }
    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }

    @ApiModelProperty(example = "卡拉卡",value = "企业简介")
    public String getEnterpriseInfo() {
        return enterpriseInfo;
    }
    public void setEnterpriseInfo(String enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }

    @ApiModelProperty(example = "www.baidu.com",value = "企业链接")
    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }
    @ApiModelProperty(example = "卡拉卡",value = "企业联系人")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }
    @ApiModelProperty(example = "110",value = "企业联系人电话")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}

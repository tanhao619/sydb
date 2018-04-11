package com.cdyoue.oddJobs.dto.zlcy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sky on 2017/9/25.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ApplicationEnterpriseSummary {


    @JsonProperty("contactPeople")
    private String contactPeople=null;

    @JsonProperty("contactNumber")
    private String contactNumber=null;

    @JsonProperty("enterpriseLogo")
    private String enterpriseLogo=null;

    @JsonProperty("enterpriseInfo")
    private String enterpriseInfo=null;

    @JsonProperty("enterpriseUrl")
    private String enterpriseUrl=null;

    /**
     * 企业联系人
     * @return id
     **/
    @ApiModelProperty(example = "卡拉卡",value = "企业联系人")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }
    /**
     * 企业联系人电话
     * @return id
     **/
    @ApiModelProperty(example = "110",value = "企业联系人电话")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    /**
     * 企业logo
     * @return id
     **/
    @ApiModelProperty(example = "1.jpg",value = "企业logo")
    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }
    /**
     * 企业简介
     * @return id
     **/
    @ApiModelProperty(example = "卡拉卡",value = "企业简介")
    public String getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(String enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }
    /**
     * 企业链接
     * @return id
     **/
    @ApiModelProperty(example = "www.baidu.com",value = "企业链接")
    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }
}

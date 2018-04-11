package com.cdyoue.oddJobs.dto.zlcy;

import io.swagger.annotations.ApiModelProperty;

/**
 * 申请创业指导的企业信息详细
 */
public class ApplicationDirectionDetail {

    private Integer id;
    private String contactPeople;
    private String contactNumber;

    @ApiModelProperty(example = "1",value = "主键")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

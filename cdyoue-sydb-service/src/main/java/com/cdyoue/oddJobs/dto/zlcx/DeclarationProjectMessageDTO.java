package com.cdyoue.oddJobs.dto.zlcx;


/**
 * Created by sky on 2017/9/25.
 */
public class DeclarationProjectMessageDTO {
    private Integer id;
    private DeclarationProjectEnterpriseDTO enterpriseInfo;
    private DeclarationProjectDTO projectInfo;
    private Integer status;
    private String contactPeople;
    private String contactNumber;
    private String attachmentUrl;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeclarationProjectEnterpriseDTO getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(DeclarationProjectEnterpriseDTO enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }

    public DeclarationProjectDTO getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(DeclarationProjectDTO projectInfo) {
        this.projectInfo = projectInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

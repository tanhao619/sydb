package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sky on 2017/9/25.
 */
@Entity
@Table(name = "sy_declaration_project_message", schema = "sydb", catalog = "")
public class SyDeclarationProjectMessageEntity {
    private int id;
    private Integer enterpriseUserId;
    private Integer projectId;
    private Byte status;
    private String contactPeople;
    private String contactNumber;
    private String attachmentUrl;
    private Timestamp createTime;
    private ProjectEntity projectEntity;


    @ManyToOne()
    @JoinColumn(name = "projectId",insertable = false,updatable = false)
    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enterpriseUserId")
    public Integer getEnterpriseUserId() {
        return enterpriseUserId;
    }

    public void setEnterpriseUserId(Integer enterpriseUserId) {
        this.enterpriseUserId = enterpriseUserId;
    }

    @Basic
    @Column(name = "projectId")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "contactPeople")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    @Basic
    @Column(name = "contactNumber")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Basic
    @Column(name = "attachmentUrl")
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyDeclarationProjectMessageEntity that = (SyDeclarationProjectMessageEntity) o;

        if (id != that.id) return false;
        if (enterpriseUserId != null ? !enterpriseUserId.equals(that.enterpriseUserId) : that.enterpriseUserId != null)
            return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (contactPeople != null ? !contactPeople.equals(that.contactPeople) : that.contactPeople != null)
            return false;
        if (contactNumber != null ? !contactNumber.equals(that.contactNumber) : that.contactNumber != null)
            return false;
        if (attachmentUrl != null ? !attachmentUrl.equals(that.attachmentUrl) : that.attachmentUrl != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enterpriseUserId != null ? enterpriseUserId.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (contactPeople != null ? contactPeople.hashCode() : 0);
        result = 31 * result + (contactNumber != null ? contactNumber.hashCode() : 0);
        result = 31 * result + (attachmentUrl != null ? attachmentUrl.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}

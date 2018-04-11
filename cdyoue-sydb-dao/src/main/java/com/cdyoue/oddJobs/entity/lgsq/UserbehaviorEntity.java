package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "userbehavior", schema = "lgsq", catalog = "")
public class UserbehaviorEntity {
    private String id;
    private Integer userId;
    private Integer userBehaviorType;
    private Integer businessEntityTypeId;
    private Integer entityId;
    private Integer domainId;
    private Timestamp createTime;

    @Id
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserBehaviorType")
    public Integer getUserBehaviorType() {
        return userBehaviorType;
    }

    public void setUserBehaviorType(Integer userBehaviorType) {
        this.userBehaviorType = userBehaviorType;
    }

    @Basic
    @Column(name = "BusinessEntityTypeId")
    public Integer getBusinessEntityTypeId() {
        return businessEntityTypeId;
    }

    public void setBusinessEntityTypeId(Integer businessEntityTypeId) {
        this.businessEntityTypeId = businessEntityTypeId;
    }

    @Basic
    @Column(name = "EntityId")
    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    @Basic
    @Column(name = "domainId")
    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    @Basic
    @Column(name = "CreateTime")
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

        UserbehaviorEntity that = (UserbehaviorEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userBehaviorType != null ? !userBehaviorType.equals(that.userBehaviorType) : that.userBehaviorType != null)
            return false;
        if (businessEntityTypeId != null ? !businessEntityTypeId.equals(that.businessEntityTypeId) : that.businessEntityTypeId != null)
            return false;
        if (entityId != null ? !entityId.equals(that.entityId) : that.entityId != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userBehaviorType != null ? userBehaviorType.hashCode() : 0);
        result = 31 * result + (businessEntityTypeId != null ? businessEntityTypeId.hashCode() : 0);
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}

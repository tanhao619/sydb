package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "lg_portal_user_portrait", schema = "lgsq", catalog = "")
public class PortalUserPortraitEntity {
    private Integer id;
    private Integer userId;
    private Integer loginType;
    private Timestamp loginTime;
    private Integer operation;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "loginType")
    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    @Basic
    @Column(name = "loginTime")
    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "operation")
    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalUserPortraitEntity that = (PortalUserPortraitEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (loginType != null ? !loginType.equals(that.loginType) : that.loginType != null) return false;
        if (loginTime != null ? !loginTime.equals(that.loginTime) : that.loginTime != null) return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (loginType != null ? loginType.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }
}

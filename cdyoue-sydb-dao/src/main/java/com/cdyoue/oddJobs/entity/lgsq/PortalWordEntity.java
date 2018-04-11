package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Entity
@Table(name = "lg_portal_word", schema = "lgsq", catalog = "")
public class PortalWordEntity {
    private Integer id;
    private UserEntity user;
    private OutsourcingProjectEntity requirementEntity;
    private String info;
    private Timestamp createTime;
    private String tel;
    private Integer wordType;

    private Integer userId;
    private Integer eventId;

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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
    @Column(name = "eventId")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalWordEntity that = (PortalWordEntity) o;

        if (id != that.id) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JsonIgnore
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    @OneToOne
    @JoinColumn(name = "eventId", insertable = false, updatable = false)
    @JsonIgnore
    public OutsourcingProjectEntity getRequirementEntity() {
        return requirementEntity;
    }

    public void setRequirementEntity(OutsourcingProjectEntity requirementEntity) {
        this.requirementEntity = requirementEntity;
    }
    @Basic
    @Column(name = "wordType")
    public Integer getWordType() {
        return wordType;
    }

    public void setWordType(Integer wordType) {
        this.wordType = wordType;
    }
}

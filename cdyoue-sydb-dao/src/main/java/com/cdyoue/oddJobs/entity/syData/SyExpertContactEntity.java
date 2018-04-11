package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dengshaojun on 2017/09/20.
 */
@Entity
@Table(name = "sy_expert_contact", schema = "sydb", catalog = "")
public class SyExpertContactEntity {
    private Integer id;
    private Integer enterId;
    private Timestamp createTime;
    private String contactPeople;
    private String contactTel;
    private String content;
    private SyExpertEntity syExpertEntity;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enter_id")
    public Integer getEnterId() {
        return enterId;
    }

    public void setEnterId(Integer enterId) {
        this.enterId = enterId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "contact_people")
    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    @Basic
    @Column(name = "contact_tel")
    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "expert_id")
    public SyExpertEntity getSyExpertEntity() {
        return syExpertEntity;
    }

    public void setSyExpertEntity(SyExpertEntity syExpertEntity) {
        this.syExpertEntity = syExpertEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertContactEntity that = (SyExpertContactEntity) o;

        if (id != that.id) return false;
        if (syExpertEntity != null ? !syExpertEntity.equals(that.syExpertEntity) : that.syExpertEntity != null) return false;
        if (enterId != null ? !enterId.equals(that.enterId) : that.enterId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (contactPeople != null ? !contactPeople.equals(that.contactPeople) : that.contactPeople != null)
            return false;
        if (contactTel != null ? !contactTel.equals(that.contactTel) : that.contactTel != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (syExpertEntity != null ? syExpertEntity.hashCode() : 0);
        result = 31 * result + (enterId != null ? enterId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (contactPeople != null ? contactPeople.hashCode() : 0);
        result = 31 * result + (contactTel != null ? contactTel.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}

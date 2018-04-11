package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dengshaojun on 2017/10/20.
 */
@Entity
@Table(name = "v_expert_contact", schema = "sydb", catalog = "")
public class SyExpertContactView {
    private Integer id;
    private Timestamp createTime;
    private String contactPeople;
    private String contactTel;
    private String content;
    private String expertName;
    private String enterName;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Basic
    @Column(name = "expert_name")
    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    @Basic
    @Column(name = "enter_name")
    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertContactView that = (SyExpertContactView) o;

        if (id != that.id) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (contactPeople != null ? !contactPeople.equals(that.contactPeople) : that.contactPeople != null)
            return false;
        if (contactTel != null ? !contactTel.equals(that.contactTel) : that.contactTel != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (expertName != null ? !expertName.equals(that.expertName) : that.expertName != null) return false;
        if (enterName != null ? !enterName.equals(that.enterName) : that.enterName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (contactPeople != null ? contactPeople.hashCode() : 0);
        result = 31 * result + (contactTel != null ? contactTel.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (expertName != null ? expertName.hashCode() : 0);
        result = 31 * result + (enterName != null ? enterName.hashCode() : 0);
        return result;
    }
}

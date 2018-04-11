package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dengshaojun on 2017/10/20.
 */
@Entity
@Table(name = "v_application_direction", schema = "sydb", catalog = "")
public class SyApplicationDirectionView {
    private Integer id;
    private String contactPeople;
    private String contactNumber;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte status;
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
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

        SyApplicationDirectionView that = (SyApplicationDirectionView) o;

        if (id != that.id) return false;
        if (contactPeople != null ? !contactPeople.equals(that.contactPeople) : that.contactPeople != null)
            return false;
        if (contactNumber != null ? !contactNumber.equals(that.contactNumber) : that.contactNumber != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (enterName != null ? !enterName.equals(that.enterName) : that.enterName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contactPeople != null ? contactPeople.hashCode() : 0);
        result = 31 * result + (contactNumber != null ? contactNumber.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (enterName != null ? enterName.hashCode() : 0);
        return result;
    }
}

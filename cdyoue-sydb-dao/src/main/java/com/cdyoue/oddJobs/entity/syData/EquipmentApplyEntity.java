package com.cdyoue.oddJobs.entity.syData;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/19.
 */
@Entity
@Table(name = "sy_equipment_apply", schema = "syData", catalog = "")
public class EquipmentApplyEntity {
    private Integer id;
    private Integer eId;
    private String contacts;
    private String phone;
    private Integer count;
    private Integer createBy;
    private Date createTime;
    private Integer status;


    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "eId")
    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    @Basic
    @Column(name = "contacts")
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "createTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createBy")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "EquipmentApplyEntity{" +
                "id=" + id +
                ", eId=" + eId +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", count=" + count +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}

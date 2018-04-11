package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Entity
@Table(name = "lg_portal_message", schema = "lgsq", catalog = "")
public class PortalMessageEntity {
    private Integer id;
    private UserEntity opera;
    private UserEntity receiver;
    private int msgType;
    private Timestamp createTime;
    private String info;
    private int eventType;
    private Boolean lookStatus;
    private Integer eventId;
    private String extra;

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
    @Column(name = "eventId")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "msgType")
    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
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
    @Column(name = "extra")
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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
    @Column(name = "eventType")
    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "lookStatus")
    public Boolean getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(Boolean lookStatus) {
        this.lookStatus = lookStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalMessageEntity that = (PortalMessageEntity) o;

        if (id != that.id) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) return false;
        if (lookStatus != null ? !lookStatus.equals(that.lookStatus) : that.lookStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (lookStatus != null ? lookStatus.hashCode() : 0);
        return result;
    }
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operaId")
    public UserEntity getOpera() {
        return opera;
    }

    public void setOpera(UserEntity opera) {
        this.opera = opera;
    }

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

}

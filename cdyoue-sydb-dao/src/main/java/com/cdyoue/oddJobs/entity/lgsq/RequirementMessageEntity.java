package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Entity
@Table(name = "lg_portal_message", schema = "lgsq", catalog = "")
public class RequirementMessageEntity {
    private Integer id;
    private UserEntity opera;
    private UserEntity receiver;
    private Byte msgType;
    private Timestamp createTime;
    private String info;
    private Byte eventType;
    private Byte lookStatus;
    private OutsourcingProjectEntity requirementEntity;
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
    @Column(name = "msgType")
    public Byte getMsgType() {
        return msgType;
    }

    public void setMsgType(Byte msgType) {
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "eventType")
    public Byte getEventType() {
        return eventType;
    }

    public void setEventType(Byte eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "lookStatus")
    public Byte getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(Byte lookStatus) {
        this.lookStatus = lookStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequirementMessageEntity that = (RequirementMessageEntity) o;

        if (id != that.id) return false;
        if (msgType != null ? !msgType.equals(that.msgType) : that.msgType != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (eventType != null ? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (lookStatus != null ? !lookStatus.equals(that.lookStatus) : that.lookStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (msgType != null ? msgType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (lookStatus != null ? lookStatus.hashCode() : 0);
        return result;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operaId")
    public UserEntity getOpera() {
        return opera;
    }

    public void setOpera(UserEntity opera) {
        this.opera = opera;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    public OutsourcingProjectEntity getRequirementEntity() {
        return requirementEntity;
    }

    public void setRequirementEntity(OutsourcingProjectEntity requirementEntity) {
        this.requirementEntity = requirementEntity;
    }
}

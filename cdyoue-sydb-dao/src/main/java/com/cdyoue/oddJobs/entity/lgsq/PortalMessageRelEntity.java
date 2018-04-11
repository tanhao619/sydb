package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/16.
 */
@Entity
@Table(name = "lg_portal_message_rel", schema = "entms", catalog = "")
public class PortalMessageRelEntity {
    private Integer id;
    private Integer messageId;
    private Integer relEventId;
    private Boolean lookStatus;
    private Integer type;
    private Date createTime;

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
    @Column(name = "messageId")
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "relEventId")
    public Integer getRelEventId() {
        return relEventId;
    }

    public void setRelEventId(Integer relEventId) {
        this.relEventId = relEventId;
    }

    @Basic
    @Column(name = "lookStatus")
    public Boolean getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(Boolean lookStatus) {
        this.lookStatus = lookStatus;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "createTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalMessageRelEntity that = (PortalMessageRelEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
        if (relEventId != null ? !relEventId.equals(that.relEventId) : that.relEventId != null) return false;
        if (lookStatus != null ? !lookStatus.equals(that.lookStatus) : that.lookStatus != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (relEventId != null ? relEventId.hashCode() : 0);
        result = 31 * result + (lookStatus != null ? lookStatus.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}

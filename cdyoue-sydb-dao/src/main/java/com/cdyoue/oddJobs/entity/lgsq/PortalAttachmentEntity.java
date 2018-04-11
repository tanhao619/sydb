package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Entity
@Table(name = "lg_portal_attachment", schema = "lgsq", catalog = "")
public class PortalAttachmentEntity {

    private Integer id;
    private Integer referId;
    private String name;
    private String url;
    private String attachmentType;
    private Byte sourceType;
    private Byte status;

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
    @Column(name = "referId")
    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "attachmentType")
    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    @Basic
    @Column(name = "sourceType")
    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }
    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalAttachmentEntity that = (PortalAttachmentEntity) o;

        if (id != that.id) return false;
        if (referId != null ? !referId.equals(that.referId) : that.referId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (attachmentType != null ? !attachmentType.equals(that.attachmentType) : that.attachmentType != null)
            return false;
        if (sourceType != null ? !sourceType.equals(that.sourceType) : that.sourceType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (referId != null ? referId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (attachmentType != null ? attachmentType.hashCode() : 0);
        result = 31 * result + (sourceType != null ? sourceType.hashCode() : 0);
        return result;
    }
}

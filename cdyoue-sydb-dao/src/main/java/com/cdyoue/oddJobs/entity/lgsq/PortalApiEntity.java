package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/6/16.
 */
@Entity
@Table(name = "lg_portal_api", schema = "lgsq", catalog = "")
public class PortalApiEntity {
    private Integer id;
    private String name;
    private String url;
    private Integer auth;
    private String descript;
    private String versionCode;
    private Timestamp createTime;
    private UserEntity createBy;
    private Timestamp updateTime;
    private Integer updateBy;
    private String params;
    private Integer type;
    private Integer method;

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
    @Column(name = "auth")
    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    @Basic
    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Basic
    @Column(name = "versionCode")
    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    @Basic
    @Column(name = "createTime")
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
    @Column(name = "updateBy")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "params")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
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
    @Column(name = "method")
    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalApiEntity that = (PortalApiEntity) o;

        if (id != that.id) return false;
        if (auth != that.auth) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (descript != null ? !descript.equals(that.descript) : that.descript != null) return false;
        if (versionCode != null ? !versionCode.equals(that.versionCode) : that.versionCode != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + auth;
        result = 31 * result + (descript != null ? descript.hashCode() : 0);
        result = 31 * result + (versionCode != null ? versionCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
    @OneToOne
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
    }
}

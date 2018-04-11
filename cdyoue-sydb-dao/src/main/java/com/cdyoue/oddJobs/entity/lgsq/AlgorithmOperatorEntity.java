package com.cdyoue.oddJobs.entity.lgsq;

import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/6/14.
 */
@Entity
@Table(name = "algorithm_operator", schema = "lgsq", catalog = "")
public class AlgorithmOperatorEntity {
    private int id;
    private String name;
    private String intro;
    private Timestamp createTime;
    private UserEntity createBy;


    private Timestamp updateTime;
    private UserEntity updateBy;

    private String dataUrl;
    private String operatorUrl;
    private AlgorithmTypeEntity type;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
    @Column(name = "dataUrl")
    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    @Basic
    @Column(name = "operatorUrl")
    public String getOperatorUrl() {
        return operatorUrl;
    }

    public void setOperatorUrl(String operatorUrl) {
        this.operatorUrl = operatorUrl;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlgorithmOperatorEntity that = (AlgorithmOperatorEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (dataUrl != null ? !dataUrl.equals(that.dataUrl) : that.dataUrl != null) return false;
        if (operatorUrl != null ? !operatorUrl.equals(that.operatorUrl) : that.operatorUrl != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (dataUrl != null ? dataUrl.hashCode() : 0);
        result = 31 * result + (operatorUrl != null ? operatorUrl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    @ManyToOne
    @JoinColumn(name = "createBy")
    public UserEntity getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserEntity createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @ManyToOne
    @JoinColumn(name = "updateBy")
    public UserEntity getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UserEntity updateBy) {
        this.updateBy = updateBy;
    }

    @OneToOne
    @JoinColumn(name = "type")
    public AlgorithmTypeEntity getType() {
        return type;
    }

    public void setType(AlgorithmTypeEntity type) {
        this.type = type;
    }
}

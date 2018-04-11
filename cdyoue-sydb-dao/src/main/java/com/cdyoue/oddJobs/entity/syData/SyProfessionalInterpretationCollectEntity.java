package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;

/**
 * Created by dengshaojun on 2017/09/27.
 */
@Entity
@Table(name = "sy_professional_interpretation_collect", schema = "sydb", catalog = "")
public class SyProfessionalInterpretationCollectEntity {
    private int id;
    private Integer userId;
    private Integer interpretationId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "interpretation_id")
    public Integer getInterpretationId() {
        return interpretationId;
    }

    public void setInterpretationId(Integer interpretationId) {
        this.interpretationId = interpretationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyProfessionalInterpretationCollectEntity that = (SyProfessionalInterpretationCollectEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (interpretationId != null ? !interpretationId.equals(that.interpretationId) : that.interpretationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (interpretationId != null ? interpretationId.hashCode() : 0);
        return result;
    }
}

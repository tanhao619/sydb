package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;

/**
 * Created by dengshaojun on 2017/09/27.
 */
@Entity
@Table(name = "sy_expert_achievement_collect", schema = "sydb", catalog = "")
public class SyExpertAchievementCollectEntity {
    private int id;
    private Integer userId;
    private Integer achievementId;

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
    @Column(name = "achievement_id")
    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achiecementId) {
        this.achievementId = achiecementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertAchievementCollectEntity that = (SyExpertAchievementCollectEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (achievementId != null ? !achievementId.equals(that.achievementId) : that.achievementId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (achievementId != null ? achievementId.hashCode() : 0);
        return result;
    }
}

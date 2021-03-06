package com.cdyoue.oddJobs.entity.syData;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Entity
@Table(name = "sy_expert_achievement", schema = "sydb", catalog = "")
public class SyExpertAchievementEntity {
    private Integer id;
    private String topImg;
    private String coverImg;
    private String name;
    private Timestamp createTime;
    private String briefIntro;
    private Integer viewCount;
    private Integer collectCount;
    private String content;
    private Timestamp updateTime;
    private Byte top;
    private Integer userId;
    private SyExpertEntity syExpertEntity;
    private Set<SyExpertAchievementCollectEntity> syExpertAchievementCollectEntitySet;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "top_img")
    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    @Basic
    @Column(name = "cover_img")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "brief_intro")
    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    @Basic
    @Column(name = "view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "collect_count")
    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "top")
    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer createrId) {
        this.userId = createrId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "expert_id")
    public SyExpertEntity getSyExpertEntity() {
        return syExpertEntity;
    }

    public void setSyExpertEntity(SyExpertEntity syExpertEntity) {
        this.syExpertEntity = syExpertEntity;
    }

    @OneToMany(cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "achievement_id")
    public Set<SyExpertAchievementCollectEntity> getSyExpertAchievementCollectEntitySet() {
        return syExpertAchievementCollectEntitySet;
    }

    public void setSyExpertAchievementCollectEntitySet(Set<SyExpertAchievementCollectEntity> syExpertAchievementCollectEntitySet) {
        this.syExpertAchievementCollectEntitySet = syExpertAchievementCollectEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyExpertAchievementEntity that = (SyExpertAchievementEntity) o;

        if (id != that.id) return false;
        if (topImg != null ? !topImg.equals(that.topImg) : that.topImg != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (briefIntro != null ? !briefIntro.equals(that.briefIntro) : that.briefIntro != null) return false;
        if (syExpertEntity != null ? !syExpertEntity.equals(that.syExpertEntity) : that.syExpertEntity != null) return false;
        if (viewCount != null ? !viewCount.equals(that.viewCount) : that.viewCount != null) return false;
        if (collectCount != null ? !collectCount.equals(that.collectCount) : that.collectCount != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (topImg != null ? topImg.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (briefIntro != null ? briefIntro.hashCode() : 0);
        result = 31 * result + (syExpertEntity != null ? syExpertEntity.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        result = 31 * result + (collectCount != null ? collectCount.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}

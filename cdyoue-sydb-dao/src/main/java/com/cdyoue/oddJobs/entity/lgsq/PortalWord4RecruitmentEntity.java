package com.cdyoue.oddJobs.entity.lgsq;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Entity
@Table(name = "lg_portal_word", schema = "lgsq", catalog = "")
public class PortalWord4RecruitmentEntity {
    @Id
    private Integer id;
    private String info;
    private Timestamp createTime;
    private String tel;
    private Integer wordType;
    private Integer userId;
    private Integer eventId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getWordType() {
        return wordType;
    }

    public void setWordType(Integer wordType) {
        this.wordType = wordType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}

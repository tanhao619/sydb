package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/15.
 */
@Entity
@Table(name = "userloghistory", schema = "lgsq", catalog = "")
public class UserLogHistoryEntity {
    private int id;
    private int userId;
    private String token;
    private String tokenType;
    private long expire;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private Timestamp freshTime;
    private String logoutReason;
    private Integer status;
    private String freshToken;


    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "tokenType")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Basic
    @Column(name = "expire")
    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    @Basic
    @Column(name = "loginTime")
    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "logoutTime")
    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    @Basic
    @Column(name = "logoutReason")
    public String getLogoutReason() {
        return logoutReason;
    }

    public void setLogoutReason(String logoutReason) {
        this.logoutReason = logoutReason;
    }


    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Basic
    @Column(name = "freshToken")
    public String getFreshToken() {
        return freshToken;
    }

    @Basic
    @Column(name = "freshTime")
    public Timestamp getFreshTime() {
        return freshTime;
    }

    public void setFreshTime(Timestamp freshTime) {
        this.freshTime = freshTime;
    }

    public void setFreshToken(String freshToken) {
        this.freshToken = freshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogHistoryEntity that = (UserLogHistoryEntity) o;

        if (userId != that.userId) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (tokenType != null ? !tokenType.equals(that.tokenType) : that.tokenType != null) return false;
        if (loginTime != null ? !loginTime.equals(that.loginTime) : that.loginTime != null) return false;
        if (logoutTime != null ? !logoutTime.equals(that.logoutTime) : that.logoutTime != null) return false;
        if (logoutReason != null ? !logoutReason.equals(that.logoutReason) : that.logoutReason != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (tokenType != null ? tokenType.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        result = 31 * result + (logoutTime != null ? logoutTime.hashCode() : 0);
        return result;
    }
}

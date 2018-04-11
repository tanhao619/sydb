package com.cdyoue.oddJobs.entity.lgsq.user;

import com.cdyoue.oddJobs.entity.lgsq.FunctioncategoryEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by liaoyoule on 2017/4/20.
 */
@Entity
@Table(name = "user", schema = "lgsq", catalog = "")
public class UserEntity {
    private Integer id;
    private Integer sex;
    private Integer age;
    private BigDecimal integrityDegree;
    private Integer expectFunctionCategory;
    private Integer expectedSalary;
    private Integer educationalBackground;
    private String userName;
    private String password;
    private String salt;
    private Timestamp createTime;
    private Timestamp refreshTime;
    private Integer workingLife;
    private Integer major;
    private String email;
    private String tel;
    private Integer userType;
    private Integer role;

    private Integer reviewStatus;
    private String reviewReason;
    private Integer pwdLv;




    private Set<PortalRealNameInfoEntity> identification;

    private UserenterpriseEntity userenterpriseEntity;
    private UserpersonalEntity userpersonalEntity;
    private FunctioncategoryEntity functioncategoryEntity;


    @Id
    @Column(name = "Id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "Age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "IntegrityDegree")
    public BigDecimal getIntegrityDegree() {
        return integrityDegree;
    }

    public void setIntegrityDegree(BigDecimal integrityDegree) {
        this.integrityDegree = integrityDegree;
    }

    @Basic
    @Column(name = "ExpectFunctionCategory")
    public Integer getExpectFunctionCategory() {
        return expectFunctionCategory;
    }

    public void setExpectFunctionCategory(Integer expectFunctionCategory) {
        this.expectFunctionCategory = expectFunctionCategory;
    }

    @Basic
    @Column(name = "ExpectedSalary")
    public Integer getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    @Basic
    @Column(name = "EducationalBackground")
    public Integer getEducationalBackground() {
        return educationalBackground;
    }

    public void setEducationalBackground(Integer educationalBackground) {
        this.educationalBackground = educationalBackground;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "CreateTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "RefreshTime")
    public Timestamp getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Timestamp refreshTime) {
        this.refreshTime = refreshTime;
    }

    @Basic
    @Column(name = "WorkingLife")
    public Integer getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(Integer workingLife) {
        this.workingLife = workingLife;
    }

    @Basic
    @Column(name = "Major")
    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "UserType")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }


    @Basic
    @Column(name = "reviewStatus")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Basic
    @Column(name = "reviewReason")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Basic
    @Column(name = "pwdLv")
    public Integer getPwdLv() {
        return pwdLv;
    }

    public void setPwdLv(Integer pwdLv) {
        this.pwdLv = pwdLv;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (integrityDegree != null ? !integrityDegree.equals(that.integrityDegree) : that.integrityDegree != null)
            return false;
        if (expectFunctionCategory != null ? !expectFunctionCategory.equals(that.expectFunctionCategory) : that.expectFunctionCategory != null)
            return false;
        if (expectedSalary != null ? !expectedSalary.equals(that.expectedSalary) : that.expectedSalary != null)
            return false;
        if (educationalBackground != null ? !educationalBackground.equals(that.educationalBackground) : that.educationalBackground != null)
            return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (salt != null ? !salt.equals(that.salt) : that.salt != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (refreshTime != null ? !refreshTime.equals(that.refreshTime) : that.refreshTime != null) return false;
        if (workingLife != null ? !workingLife.equals(that.workingLife) : that.workingLife != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (pwdLv != null ? !pwdLv.equals(that.pwdLv) : that.pwdLv != null) return false;
        if (userenterpriseEntity != null ? !userenterpriseEntity.equals(that.userenterpriseEntity) : that.userenterpriseEntity != null)
            return false;
        if (userpersonalEntity != null ? !userpersonalEntity.equals(that.userpersonalEntity) : that.userpersonalEntity != null)
            return false;
        return functioncategoryEntity != null ? functioncategoryEntity.equals(that.functioncategoryEntity) : that.functioncategoryEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (integrityDegree != null ? integrityDegree.hashCode() : 0);
        result = 31 * result + (expectFunctionCategory != null ? expectFunctionCategory.hashCode() : 0);
        result = 31 * result + (expectedSalary != null ? expectedSalary.hashCode() : 0);
        result = 31 * result + (educationalBackground != null ? educationalBackground.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (refreshTime != null ? refreshTime.hashCode() : 0);
        result = 31 * result + (workingLife != null ? workingLife.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (pwdLv != null ? pwdLv.hashCode() : 0);
        result = 31 * result + (userenterpriseEntity != null ? userenterpriseEntity.hashCode() : 0);
        result = 31 * result + (userpersonalEntity != null ? userpersonalEntity.hashCode() : 0);
        result = 31 * result + (functioncategoryEntity != null ? functioncategoryEntity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", sex=" + sex +
                ", age=" + age +
                ", integrityDegree=" + integrityDegree +
                ", expectFunctionCategory=" + expectFunctionCategory +
                ", expectedSalary=" + expectedSalary +
                ", educationalBackground=" + educationalBackground +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", refreshTime=" + refreshTime +
                ", workingLife=" + workingLife +
                ", major=" + major +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userType=" + userType +
                ", role=" + role +
                ", reviewStatus=" + reviewStatus +
                ", pwdLv=" + pwdLv +
                ", userenterpriseEntity=" + userenterpriseEntity +
                ", userpersonalEntity=" + userpersonalEntity +
                ", functioncategoryEntity=" + functioncategoryEntity +
                '}';
    }


    /**
     * 当usertype 为 1 企业关系
     * @return
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "id",referencedColumnName = "UserId",insertable = false,updatable = false)
    public UserenterpriseEntity getUserenterpriseEntity() {
        return userenterpriseEntity;
    }

    public void setUserenterpriseEntity(UserenterpriseEntity userenterpriseEntity) {
        this.userenterpriseEntity = userenterpriseEntity;
    }


    /**
     * 当 usertype 为 0 个人关系
     * @return
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "id",referencedColumnName = "UserId",insertable = false,updatable = false)
    public UserpersonalEntity getUserpersonalEntity() {
        return userpersonalEntity;
    }

    public void setUserpersonalEntity(UserpersonalEntity userpersonalEntity) {
        this.userpersonalEntity = userpersonalEntity;
    }


    /**
     *
     * @return
     */

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expectFunctionCategory",referencedColumnName = "id",insertable = false,updatable = false)
    public FunctioncategoryEntity getFunctioncategoryEntity() {
        return functioncategoryEntity;
    }

    public void setFunctioncategoryEntity(FunctioncategoryEntity functioncategoryEntity) {
        this.functioncategoryEntity = functioncategoryEntity;
    }


    /**
     * 认证关系
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public Set<PortalRealNameInfoEntity> getIdentification() {
        return identification;
    }

    public void setIdentification(Set<PortalRealNameInfoEntity> identification) {
        this.identification = identification;
    }
}

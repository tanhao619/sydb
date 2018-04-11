package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "enterpriserecruitment", schema = "lgsq", catalog = "")
public class EnterpriserecruitmentEntity {
    private Integer id;
    private Timestamp createTime;
    private Timestamp refreshTime;
    private String jobName;
    private String jobDesc;
    private Integer age;
    private Integer sex;
    private Integer expectFunctionCategory;
    private Integer expectedSalary;
    private Integer educationalBackground;
    private Integer workingLife;
    private Integer major;
    private Integer entId;
    private String workPlace;
    private String welfare;
    private String jobRequire;
    private String contact;
    private String tel;
    private Double recommendedDegree;
    private Double orderNo;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "JobName")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "JobDesc")
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
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
    @Column(name = "Sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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
    @Column(name = "EntID")
    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    @Basic
    @Column(name = "WorkPlace")
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "Welfare")
    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    @Basic
    @Column(name = "JobRequire")
    public String getJobRequire() {
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
    @Column(name = "RecommendedDegree")
    public Double getRecommendedDegree() {
        return recommendedDegree;
    }

    public void setRecommendedDegree(Double recommendedDegree) {
        this.recommendedDegree = recommendedDegree;
    }

    @Basic
    @Column(name = "orderNo")
    public Double getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Double orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterpriserecruitmentEntity that = (EnterpriserecruitmentEntity) o;

        if (id != that.id) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (refreshTime != null ? !refreshTime.equals(that.refreshTime) : that.refreshTime != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (jobDesc != null ? !jobDesc.equals(that.jobDesc) : that.jobDesc != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (expectFunctionCategory != null ? !expectFunctionCategory.equals(that.expectFunctionCategory) : that.expectFunctionCategory != null)
            return false;
        if (expectedSalary != null ? !expectedSalary.equals(that.expectedSalary) : that.expectedSalary != null)
            return false;
        if (educationalBackground != null ? !educationalBackground.equals(that.educationalBackground) : that.educationalBackground != null)
            return false;
        if (workingLife != null ? !workingLife.equals(that.workingLife) : that.workingLife != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (entId != null ? !entId.equals(that.entId) : that.entId != null) return false;
        if (workPlace != null ? !workPlace.equals(that.workPlace) : that.workPlace != null) return false;
        if (welfare != null ? !welfare.equals(that.welfare) : that.welfare != null) return false;
        if (jobRequire != null ? !jobRequire.equals(that.jobRequire) : that.jobRequire != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (recommendedDegree != null ? !recommendedDegree.equals(that.recommendedDegree) : that.recommendedDegree != null)
            return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (refreshTime != null ? refreshTime.hashCode() : 0);
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobDesc != null ? jobDesc.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (expectFunctionCategory != null ? expectFunctionCategory.hashCode() : 0);
        result = 31 * result + (expectedSalary != null ? expectedSalary.hashCode() : 0);
        result = 31 * result + (educationalBackground != null ? educationalBackground.hashCode() : 0);
        result = 31 * result + (workingLife != null ? workingLife.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (entId != null ? entId.hashCode() : 0);
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        result = 31 * result + (welfare != null ? welfare.hashCode() : 0);
        result = 31 * result + (jobRequire != null ? jobRequire.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (recommendedDegree != null ? recommendedDegree.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        return result;
    }
}

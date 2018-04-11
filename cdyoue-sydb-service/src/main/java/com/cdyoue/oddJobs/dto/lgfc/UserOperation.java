package com.cdyoue.oddJobs.dto.lgfc;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tr on 2017/5/27.
 */
public class UserOperation {
    private Integer userid;
    private Integer enterpriserecruitmenttimes;
    private Integer moonlightingtimes;
    private Integer investmenttimes;
    private Integer outsourcingprojecttimes;
    private Integer questiontimes;
    private Integer newstimes;
    private Integer patenttimes;
    private Integer policytimes;
    private Integer oddjobtimes;
    private Integer mastertimes;
    private Integer tutortimes;
    private Integer experttimes;
    private Integer agenttimes;
    private Integer activitytimes;
    private Integer fieldtimes;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @ApiModelProperty( value = "企业招聘次数")
    public Integer getEnterpriserecruitmenttimes() {
        return enterpriserecruitmenttimes;
    }

    public void setEnterpriserecruitmenttimes(Integer enterpriserecruitmenttimes) {
        this.enterpriserecruitmenttimes = enterpriserecruitmenttimes;
    }

    @ApiModelProperty( value = "兼职工作次数")
    public Integer getMoonlightingtimes() {
        return moonlightingtimes;
    }

    public void setMoonlightingtimes(Integer moonlightingtimes) {
        this.moonlightingtimes = moonlightingtimes;
    }

    @ApiModelProperty( value = "投融资次数")
    public Integer getInvestmenttimes() {
        return investmenttimes;
    }

    public void setInvestmenttimes(Integer investmenttimes) {
        this.investmenttimes = investmenttimes;
    }

    @ApiModelProperty( value = "外包项目")
    public Integer getOutsourcingprojecttimes() {
        return outsourcingprojecttimes;
    }

    public void setOutsourcingprojecttimes(Integer outsourcingprojecttimes) {
        this.outsourcingprojecttimes = outsourcingprojecttimes;
    }

    @ApiModelProperty( value = "问题次数")
    public Integer getQuestiontimes() {
        return questiontimes;
    }

    public void setQuestiontimes(Integer questiontimes) {
        this.questiontimes = questiontimes;
    }

    @ApiModelProperty( value = "新闻次数")
    public Integer getNewstimes() {
        return newstimes;
    }

    public void setNewstimes(Integer newstimes) {
        this.newstimes = newstimes;
    }

    @ApiModelProperty( value = "专利次数")
    public Integer getPatenttimes() {
        return patenttimes;
    }

    public void setPatenttimes(Integer patenttimes) {
        this.patenttimes = patenttimes;
    }

    @ApiModelProperty( value = "政策次数")
    public Integer getPolicytimes() {
        return policytimes;
    }

    public void setPolicytimes(Integer policytimes) {
        this.policytimes = policytimes;
    }

    @ApiModelProperty( value = "零工次数")
    public Integer getOddjobtimes() {
        return oddjobtimes;
    }

    public void setOddjobtimes(Integer oddjobtimes) {
        this.oddjobtimes = oddjobtimes;
    }

    @ApiModelProperty( value = "大咔次数")
    public Integer getMastertimes() {
        return mastertimes;
    }

    public void setMastertimes(Integer mastertimes) {
        this.mastertimes = mastertimes;
    }

    @ApiModelProperty( value = "导师次数")
    public Integer getTutortimes() {
        return tutortimes;
    }

    public void setTutortimes(Integer tutortimes) {
        this.tutortimes = tutortimes;
    }

    @ApiModelProperty( value = "专家次数")
    public Integer getExperttimes() {
        return experttimes;
    }

    public void setExperttimes(Integer experttimes) {
        this.experttimes = experttimes;
    }

    @ApiModelProperty( value = "经纪人次数")
    public Integer getAgenttimes() {
        return agenttimes;
    }

    public void setAgenttimes(Integer agenttimes) {
        this.agenttimes = agenttimes;
    }

    @ApiModelProperty( value = "活动次数")
    public Integer getActivitytimes() {
        return activitytimes;
    }

    public void setActivitytimes(Integer activitytimes) {
        this.activitytimes = activitytimes;
    }

    @ApiModelProperty( value = "场地次数")
    public Integer getFieldtimes() {
        return fieldtimes;
    }

    public void setFieldtimes(Integer fieldtimes) {
        this.fieldtimes = fieldtimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOperation that = (UserOperation) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (enterpriserecruitmenttimes != null ? !enterpriserecruitmenttimes.equals(that.enterpriserecruitmenttimes) : that.enterpriserecruitmenttimes != null)
            return false;
        if (moonlightingtimes != null ? !moonlightingtimes.equals(that.moonlightingtimes) : that.moonlightingtimes != null)
            return false;
        if (investmenttimes != null ? !investmenttimes.equals(that.investmenttimes) : that.investmenttimes != null)
            return false;
        if (outsourcingprojecttimes != null ? !outsourcingprojecttimes.equals(that.outsourcingprojecttimes) : that.outsourcingprojecttimes != null)
            return false;
        if (questiontimes != null ? !questiontimes.equals(that.questiontimes) : that.questiontimes != null)
            return false;
        if (newstimes != null ? !newstimes.equals(that.newstimes) : that.newstimes != null) return false;
        if (patenttimes != null ? !patenttimes.equals(that.patenttimes) : that.patenttimes != null) return false;
        if (policytimes != null ? !policytimes.equals(that.policytimes) : that.policytimes != null) return false;
        if (oddjobtimes != null ? !oddjobtimes.equals(that.oddjobtimes) : that.oddjobtimes != null) return false;
        if (mastertimes != null ? !mastertimes.equals(that.mastertimes) : that.mastertimes != null) return false;
        if (tutortimes != null ? !tutortimes.equals(that.tutortimes) : that.tutortimes != null) return false;
        if (experttimes != null ? !experttimes.equals(that.experttimes) : that.experttimes != null) return false;
        if (agenttimes != null ? !agenttimes.equals(that.agenttimes) : that.agenttimes != null) return false;
        if (activitytimes != null ? !activitytimes.equals(that.activitytimes) : that.activitytimes != null)
            return false;
        return fieldtimes != null ? fieldtimes.equals(that.fieldtimes) : that.fieldtimes == null;

    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (enterpriserecruitmenttimes != null ? enterpriserecruitmenttimes.hashCode() : 0);
        result = 31 * result + (moonlightingtimes != null ? moonlightingtimes.hashCode() : 0);
        result = 31 * result + (investmenttimes != null ? investmenttimes.hashCode() : 0);
        result = 31 * result + (outsourcingprojecttimes != null ? outsourcingprojecttimes.hashCode() : 0);
        result = 31 * result + (questiontimes != null ? questiontimes.hashCode() : 0);
        result = 31 * result + (newstimes != null ? newstimes.hashCode() : 0);
        result = 31 * result + (patenttimes != null ? patenttimes.hashCode() : 0);
        result = 31 * result + (policytimes != null ? policytimes.hashCode() : 0);
        result = 31 * result + (oddjobtimes != null ? oddjobtimes.hashCode() : 0);
        result = 31 * result + (mastertimes != null ? mastertimes.hashCode() : 0);
        result = 31 * result + (tutortimes != null ? tutortimes.hashCode() : 0);
        result = 31 * result + (experttimes != null ? experttimes.hashCode() : 0);
        result = 31 * result + (agenttimes != null ? agenttimes.hashCode() : 0);
        result = 31 * result + (activitytimes != null ? activitytimes.hashCode() : 0);
        result = 31 * result + (fieldtimes != null ? fieldtimes.hashCode() : 0);
        return result;
    }
}

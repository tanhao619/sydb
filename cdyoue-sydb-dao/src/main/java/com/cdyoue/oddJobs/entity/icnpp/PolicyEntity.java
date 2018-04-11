package com.cdyoue.oddJobs.entity.icnpp;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/6/21.
 */
@Entity
@Table(name = "ydntzc_policy", schema = "icnpp", catalog = "")
public class PolicyEntity {
    private String id;
    private String title;
    private String issuedNo;
    private String issuedUserId;
    private String organization;
    private String issuedTime;
    private String modifyTime;
    private String policyThemeId;
    private String timeStageId;
    private String interviewOnline;
    private Integer policyOrder;
    private String policyStick;
    private String policyFlag;
    private String policyImagePreviewPic;
    private String policyInterpretPreviewPic;
    private String policyInterviewPreviewPic;
    private String policyInterpretShortContent;
    private String interviewTime;
    private String interviewGuest;
    private String interviewShortContent;
    private String areaFlag;
    private String policyAreaId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "issuedNo")
    public String getIssuedNo() {
        return issuedNo;
    }
    @Basic
    @Column(name = "issuedUserId")
    public String getIssuedUserId() {
        return issuedUserId;
    }

    public void setIssuedUserId(String issuedUserId) {
        this.issuedUserId = issuedUserId;
    }

    public void setIssuedNo(String issuedNo) {
        this.issuedNo = issuedNo;
    }
    @Basic
    @Column(name = "issuedTime")
    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    @Basic
    @Column(name = "modifyTime")
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "policyThemeId")
    public String getPolicyThemeId() {
        return policyThemeId;
    }

    public void setPolicyThemeId(String policyThemeId) {
        this.policyThemeId = policyThemeId;
    }

    @Basic
    @Column(name = "timeStageId")
    public String getTimeStageId() {
        return timeStageId;
    }

    public void setTimeStageId(String timeStageId) {
        this.timeStageId = timeStageId;
    }

    @Basic
    @Column(name = "interviewOnline")
    public String getInterviewOnline() {
        return interviewOnline;
    }

    public void setInterviewOnline(String interviewOnline) {
        this.interviewOnline = interviewOnline;
    }

    @Basic
    @Column(name = "policyOrder")
    public Integer getPolicyOrder() {
        return policyOrder;
    }

    public void setPolicyOrder(Integer policyOrder) {
        this.policyOrder = policyOrder;
    }

    @Basic
    @Column(name = "policyStick")
    public String getPolicyStick() {
        return policyStick;
    }

    public void setPolicyStick(String policyStick) {
        this.policyStick = policyStick;
    }

    @Basic
    @Column(name = "policyFlag")
    public String getPolicyFlag() {
        return policyFlag;
    }

    public void setPolicyFlag(String policyFlag) {
        this.policyFlag = policyFlag;
    }

    @Basic
    @Column(name = "policyImagePreviewPic")
    public String getPolicyImagePreviewPic() {
        return policyImagePreviewPic;
    }

    public void setPolicyImagePreviewPic(String policyImagePreviewPic) {
        this.policyImagePreviewPic = policyImagePreviewPic;
    }

    @Basic
    @Column(name = "policyInterpretPreviewPic")
    public String getPolicyInterpretPreviewPic() {
        return policyInterpretPreviewPic;
    }

    public void setPolicyInterpretPreviewPic(String policyInterpretPreviewPic) {
        this.policyInterpretPreviewPic = policyInterpretPreviewPic;
    }

    @Basic
    @Column(name = "policyInterviewPreviewPic")
    public String getPolicyInterviewPreviewPic() {
        return policyInterviewPreviewPic;
    }

    public void setPolicyInterviewPreviewPic(String policyInterviewPreviewPic) {
        this.policyInterviewPreviewPic = policyInterviewPreviewPic;
    }

    @Basic
    @Column(name = "policyInterpretShortContent")
    public String getPolicyInterpretShortContent() {
        return policyInterpretShortContent;
    }

    public void setPolicyInterpretShortContent(String policyInterpretShortContent) {
        this.policyInterpretShortContent = policyInterpretShortContent;
    }

    @Basic
    @Column(name = "interviewTime")
    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    @Basic
    @Column(name = "interviewGuest")
    public String getInterviewGuest() {
        return interviewGuest;
    }

    public void setInterviewGuest(String interviewGuest) {
        this.interviewGuest = interviewGuest;
    }

    @Basic
    @Column(name = "interviewShortContent")
    public String getInterviewShortContent() {
        return interviewShortContent;
    }

    public void setInterviewShortContent(String interviewShortContent) {
        this.interviewShortContent = interviewShortContent;
    }

    @Basic
    @Column(name = "areaFlag")
    public String getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(String areaFlag) {
        this.areaFlag = areaFlag;
    }

    @Basic
    @Column(name = "policyAreaId")
    public String getPolicyAreaId() {
        return policyAreaId;
    }

    public void setPolicyAreaId(String policyAreaId) {
        this.policyAreaId = policyAreaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PolicyEntity that = (PolicyEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (issuedNo != null ? !issuedNo.equals(that.issuedNo) : that.issuedNo != null) return false;
        if (issuedTime != null ? !issuedTime.equals(that.issuedTime) : that.issuedTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (policyThemeId != null ? !policyThemeId.equals(that.policyThemeId) : that.policyThemeId != null)
            return false;
        if (timeStageId != null ? !timeStageId.equals(that.timeStageId) : that.timeStageId != null) return false;
        if (interviewOnline != null ? !interviewOnline.equals(that.interviewOnline) : that.interviewOnline != null)
            return false;
        if (policyOrder != null ? !policyOrder.equals(that.policyOrder) : that.policyOrder != null) return false;
        if (policyStick != null ? !policyStick.equals(that.policyStick) : that.policyStick != null) return false;
        if (policyFlag != null ? !policyFlag.equals(that.policyFlag) : that.policyFlag != null) return false;
        if (policyImagePreviewPic != null ? !policyImagePreviewPic.equals(that.policyImagePreviewPic) : that.policyImagePreviewPic != null)
            return false;
        if (policyInterpretPreviewPic != null ? !policyInterpretPreviewPic.equals(that.policyInterpretPreviewPic) : that.policyInterpretPreviewPic != null)
            return false;
        if (policyInterviewPreviewPic != null ? !policyInterviewPreviewPic.equals(that.policyInterviewPreviewPic) : that.policyInterviewPreviewPic != null)
            return false;
        if (policyInterpretShortContent != null ? !policyInterpretShortContent.equals(that.policyInterpretShortContent) : that.policyInterpretShortContent != null)
            return false;
        if (interviewTime != null ? !interviewTime.equals(that.interviewTime) : that.interviewTime != null)
            return false;
        if (interviewGuest != null ? !interviewGuest.equals(that.interviewGuest) : that.interviewGuest != null)
            return false;
        if (interviewShortContent != null ? !interviewShortContent.equals(that.interviewShortContent) : that.interviewShortContent != null)
            return false;
        if (areaFlag != null ? !areaFlag.equals(that.areaFlag) : that.areaFlag != null) return false;
        if (policyAreaId != null ? !policyAreaId.equals(that.policyAreaId) : that.policyAreaId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (issuedNo != null ? issuedNo.hashCode() : 0);
        result = 31 * result + (issuedTime != null ? issuedTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (policyThemeId != null ? policyThemeId.hashCode() : 0);
        result = 31 * result + (timeStageId != null ? timeStageId.hashCode() : 0);
        result = 31 * result + (interviewOnline != null ? interviewOnline.hashCode() : 0);
        result = 31 * result + (policyOrder != null ? policyOrder.hashCode() : 0);
        result = 31 * result + (policyStick != null ? policyStick.hashCode() : 0);
        result = 31 * result + (policyFlag != null ? policyFlag.hashCode() : 0);
        result = 31 * result + (policyImagePreviewPic != null ? policyImagePreviewPic.hashCode() : 0);
        result = 31 * result + (policyInterpretPreviewPic != null ? policyInterpretPreviewPic.hashCode() : 0);
        result = 31 * result + (policyInterviewPreviewPic != null ? policyInterviewPreviewPic.hashCode() : 0);
        result = 31 * result + (policyInterpretShortContent != null ? policyInterpretShortContent.hashCode() : 0);
        result = 31 * result + (interviewTime != null ? interviewTime.hashCode() : 0);
        result = 31 * result + (interviewGuest != null ? interviewGuest.hashCode() : 0);
        result = 31 * result + (interviewShortContent != null ? interviewShortContent.hashCode() : 0);
        result = 31 * result + (areaFlag != null ? areaFlag.hashCode() : 0);
        result = 31 * result + (policyAreaId != null ? policyAreaId.hashCode() : 0);
        return result;
    }
    @Formula("(SELECT org.organizationName FROM ydntqx_organization org,ydntzc_usertoorganization uto,ydntzc_policy p WHERE org.organizationId = uto.organizationId AND uto.userId = p.issuedUserId and p.id =id)")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}

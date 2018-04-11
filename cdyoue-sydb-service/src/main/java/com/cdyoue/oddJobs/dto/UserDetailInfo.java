package com.cdyoue.oddJobs.dto;

/**
 * Created by liaoyoule on 2017/4/24.
 */
public class UserDetailInfo {


    private Integer id;
    private String name;
    private String info;


    private String headImg;
    private String industry = null;
    private String expertType;
    private String position = null;

    private String sentence = null;

    private Integer dataComp = null;

    private Integer invitedNum = null;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getDataComp() {
        return dataComp;
    }

    public void setDataComp(Integer dataComp) {
        this.dataComp = dataComp;
    }

    public Integer getInvitedNum() {
        return invitedNum;
    }

    public void setInvitedNum(Integer invitedNum) {
        this.invitedNum = invitedNum;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }
}

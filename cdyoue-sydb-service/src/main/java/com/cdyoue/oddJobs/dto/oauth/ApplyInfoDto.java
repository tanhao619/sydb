package com.cdyoue.oddJobs.dto.oauth;

/**
 * Created by Tangguang on 2017/6/3.
 */
public class ApplyInfoDto {

    private int id;//ID
    private String name;//名称
    private Integer type;//类型
    private Integer reviewStatus;//认证状态
    private String frontImg;//正面照片
    private String cardNo;//身份证/营业执照编号
    private String reviewReason;//审核原因
    private String backImg;//反面照片
    private Integer look;//查看标志

    public int getId() {
        return id;
    }

    public void setId( int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType( Integer type) {
        this.type = type;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus( Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg( String frontImg) {
        this.frontImg = frontImg;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo( String cardNo) {
        this.cardNo = cardNo;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(final String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(   String backImg) {
        this.backImg = backImg;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }
}

package com.cdyoue.oddJobs.dto.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by dengshaojun on 2017/5/9.
 * 个人/企业 实名认证的表单信息
 */
public class RealNameApplyInfo {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("cardNo")
    private String cardNo = null;

    @JsonProperty("frontImg")
    private String frontImg = null;

    @JsonProperty("backImg")
    private String backImg = null;


    public RealNameApplyInfo name(String name) {
        this.name = name;
        return this;
    }
    /**
     * 身份证姓名/企业名称
     * @return name
     */
    @ApiModelProperty(example = "马云/优易数据", value = "身份证姓名/企业名称")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public RealNameApplyInfo cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }
    /**
     * 身份证号码/营业执照注册号
     * @return cardNo
     */
    @ApiModelProperty(example = "51010120170509205X", value = "身份证号码/营业执照注册号")
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public RealNameApplyInfo frontImg(String frontImg) {
        this.frontImg = frontImg;
        return this;
    }
    /**
     * 身份证正面图片/营业执照正面图片的url
     * @return frontImg
     */
    @ApiModelProperty(example = "http://www.youedata.com/pic/123.jpg", value = "身份证正面图片/营业执照正面图片（调用上传公共附件API，得到图片的url）")
    public String getFrontImg() {
        return frontImg;
    }
    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }


    public RealNameApplyInfo backImg(String backImg) {
        this.backImg = backImg;
        return this;
    }
    /**
     * 身份证背面图片/营业执照背面图片的url
     * @return backImg
     */
    @ApiModelProperty(example = "http://www.youedata.com/pic/145.jpg", value = "身份证背面图片/营业执照正面图片（调用上传公共附件API，得到图片的url）")
    public String getBackImg() {
        return backImg;
    }
    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

}

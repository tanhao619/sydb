package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liaoyoule on 2017/6/9.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class AccountSumary {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("accountBase")
    private AccountBase accountBase;
    @JsonProperty("reviewStatus")
    private Integer reviewStatus;
    @JsonProperty("reviewReason")
    private String reviewReason;
    @JsonProperty("isReferCert")
    private Boolean isReferCert;


    @ApiModelProperty(value = "账户基本信息")
    public AccountBase getAccountBase() {
        return accountBase;
    }

    public void setAccountBase(AccountBase accountBase) {
        this.accountBase = accountBase;
    }
    @ApiModelProperty(value = "账户是否被禁止 1正常  2 禁止 ")
    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
    @ApiModelProperty(value = "禁止原因")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }
    @ApiModelProperty(value = "用户id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(value = "是否提交认证信息  是:true 否 false")
    public Boolean getReferCert() {
        return isReferCert;
    }

    public void setReferCert(Boolean referCert) {
        isReferCert = referCert;
    }
}

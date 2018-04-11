package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2017/5/8.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class EnterAccountDetail {
    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("headImg")
    private String headImg;

    @JsonProperty("establishmentTime")
    private String establishmentTime;

    @JsonProperty("location")
    private String location;

    @JsonProperty("registeredCapital")
    private Integer registeredCapital;

    @JsonProperty("legalPerson")
    private String legalPerson;

    @JsonProperty("organizationCode")
    private String organizationCode;

    @JsonProperty("info")
    private String info;

    @JsonProperty("business")
    private String business;

    @JsonProperty("enterpriseDetail")
     private String enterpriseDetail;

    @JsonProperty("enterType")
    private String enterType;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("email")//邮箱
    private String email;
    @JsonProperty("tel")//电话
    private String tel;

    @JsonProperty("dataComp")
    private Integer dataComp;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(final String tel) {
        this.tel = tel;
    }

    /**
     * 用户ID
     * @return id
     **/
    @ApiModelProperty(example = "1", value = "用户ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 所属行业类别
     * @return
     */
    @ApiModelProperty(example = "1", value = "所属行业ID")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 企业名称
     * @return name
     **/
    @ApiModelProperty(example = "优易数据", value = "企业名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 企业头像
     * @return headImg
     **/
    @ApiModelProperty(example = "/xxx/xxx/xxx.jpg", value = "企业头像")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 企业成立时间
     * @return establishmentTime
     **/
    @ApiModelProperty(example = "2017/5/8", value = "企业成立时间")
    public String getEstablishmentTime() {
        return establishmentTime;
    }

    public void setEstablishmentTime(String establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    /**
     * 企业所在地
     * @return location
     **/
    @ApiModelProperty(example = "成都", value = "所在地")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 企业注册资金
     * @return registeredCapital
     **/
    @ApiModelProperty(example = " 5000", value = "注册资金")
    public Integer getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Integer registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    /**
     * 企业法人
     * @return legalPerson
     **/
    @ApiModelProperty(example = "XXX", value = "企业法人")
    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * 企业类型
     * @return enterType
     **/
    @ApiModelProperty(example = "企业类型", value = "企业类型")
    public String getEnterType() {
        return enterType;
    }

    public void setEnterType(String enterType) {
        this.enterType = enterType;
    }

    /**
     * 组织机构代码
     * @return organizationCode
     **/
    @ApiModelProperty(example = " 335528376", value = "组织机构代码")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * 企业简介
     * @return info
     **/
    @ApiModelProperty(example = "企业简介！", value = "企业简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 企业业务范围
     * @return business
     **/
    @ApiModelProperty(example = "企业业务范围", value = "企业业务范围")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * 企业详细信息
     * @return
     * @ApiModelProperty(example = "企业详细信息, value = "企业详细信息")
     */
    public String getEnterpriseDetail() {
        return enterpriseDetail;
    }

    public void setEnterpriseDetail(String enterpriseDetail) {
        this.enterpriseDetail = enterpriseDetail;
    }

    public Integer getDataComp() {
        return dataComp;
    }

    public void setDataComp(final Integer dataComp) {
        this.dataComp = dataComp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId,name, headImg,establishmentTime,location,registeredCapital,
                legalPerson,organizationCode,info,business,enterpriseDetail,categoryId);
    }
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnterAccountDetail enterAccount = (EnterAccountDetail) o;
        return Objects.equals(this.userId, enterAccount.userId) &&
                Objects.equals(this.name, enterAccount.name) &&
                Objects.equals(this.headImg, enterAccount.headImg) &&
                Objects.equals(this.establishmentTime, enterAccount.establishmentTime)&&
                Objects.equals(this.location, enterAccount.location)&&
                Objects.equals(this.registeredCapital, enterAccount.registeredCapital)&&
                Objects.equals(this.legalPerson, enterAccount.legalPerson)&&
                Objects.equals(this.organizationCode, enterAccount.organizationCode)&&
                Objects.equals(this.info, enterAccount.info)&&
                Objects.equals(this.business, enterAccount.business)&&
                Objects.equals(this.categoryId, enterAccount.categoryId)&&
                Objects.equals(this.enterpriseDetail, enterAccount.enterpriseDetail)&&
                Objects.equals(this.tel, enterAccount.tel)&&
                Objects.equals(this.email, enterAccount.email) &&
                Objects.equals(this.dataComp, enterAccount.dataComp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EnterAccountDetail {\n");
        sb.append("    id: ").append(toIndentedString(userId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    email: ").append(toIndentedString(headImg)).append("\n");
        sb.append("    password: ").append(toIndentedString(establishmentTime)).append("\n");
        sb.append("    username: ").append(toIndentedString(location)).append("\n");
        sb.append("    email: ").append(toIndentedString(registeredCapital)).append("\n");
        sb.append("    password: ").append(toIndentedString(legalPerson)).append("\n");
        sb.append("    username: ").append(toIndentedString(organizationCode)).append("\n");
        sb.append("    email: ").append(toIndentedString(info)).append("\n");
        sb.append("    password: ").append(toIndentedString(business)).append("\n");
        sb.append("    enterpriseDetail: ").append(toIndentedString(enterpriseDetail)).append("\n");
        sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
        sb.append("    tel: ").append(toIndentedString(tel)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    dataComp: ").append(toIndentedString(dataComp)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

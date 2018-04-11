package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by Administrator on 2017/5/12.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class EnterAccountSumary {
    @JsonProperty("name")//企业名称
    private String name;

    @JsonProperty("headImg")//头像
    private String headImg;

    @JsonProperty("establishmentTime")//创建时间
    private String establishmentTime;

    @JsonProperty("expectFunctionCategory")//所在行业
    private int expectFunctionCategory;

    @JsonProperty("location")//所在地点
    private String location;

    @JsonProperty("registeredCapital")//注册资金
    private Integer registeredCapital;

    @JsonProperty("legalPerson")//法人
    private String legalPerson;

    @JsonProperty("enterType")//企业类型
    private String enterType;

    @JsonProperty("organizationCode")//组织机构代码
    private String organizationCode;

    @JsonProperty("info")//简介
    private String info;

    @JsonProperty("business")//业务范围
    private String business;

    @JsonProperty("enterpriseDetail")//详情
    private String enterpriseDetail;

    @JsonProperty("email")//邮箱
    private String email;

    @JsonProperty("tel")//电话
    private String tel;

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
    @ApiModelProperty(example = "2017/5/12", value = "企业成立时间")
    public String getEstablishmentTime() {
        return establishmentTime;
    }

    public void setEstablishmentTime(String establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    /**
     * 企业所在行业
     * @return ExpectFunctionCategory
     **/
    @ApiModelProperty(example = "1", value = "企业所在行业")
    public int getExpectFunctionCategory() {
        return expectFunctionCategory;
    }

    public void setExpectFunctionCategory(int expectFunctionCategory) {
        this.expectFunctionCategory = expectFunctionCategory;
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
     * 经营范围
     * @return
     */
    @ApiModelProperty(example = "企业经营范围！", value = "企业经营范围")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * 详细信息
     * @return
     */
    @ApiModelProperty(example = "企业详细信息！", value = "企业详悉信息")
    public String getEnterpriseDetail() {
        return enterpriseDetail;
    }

    public void setEnterpriseDetail(String enterpriseDetail) {
        this.enterpriseDetail = enterpriseDetail;
    }

    @Email(message = "不是标准的邮箱格式")
    @ApiModelProperty(example = "mmp@baidu.com！", value = "邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$", message = "不是标准的手机号码")
    @ApiModelProperty(example = "13990012345！", value = "电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, headImg, establishmentTime, location, expectFunctionCategory, registeredCapital,
                legalPerson,organizationCode,info,business,enterpriseDetail);
    }
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnterAccountSumary enterAccount = (EnterAccountSumary) o;
        return Objects.equals(this.name, enterAccount.name) &&
                Objects.equals(this.headImg, enterAccount.headImg) &&
                Objects.equals(this.establishmentTime, enterAccount.establishmentTime)&&
                Objects.equals(this.location, enterAccount.location)&&
                Objects.equals(this.expectFunctionCategory, enterAccount.expectFunctionCategory) &&
                Objects.equals(this.registeredCapital, enterAccount.registeredCapital)&&
                Objects.equals(this.legalPerson, enterAccount.legalPerson)&&
                Objects.equals(this.organizationCode, enterAccount.organizationCode)&&
                Objects.equals(this.business, enterAccount.business)&&
                Objects.equals(this.enterpriseDetail, enterAccount.enterpriseDetail)&&
                Objects.equals(this.info, enterAccount.info);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EnterAccountDetail {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    email: ").append(toIndentedString(headImg)).append("\n");
        sb.append("    password: ").append(toIndentedString(establishmentTime)).append("\n");
        sb.append("    username: ").append(toIndentedString(location)).append("\n");
        sb.append("    username: ").append(toIndentedString(expectFunctionCategory)).append("\n");
        sb.append("    email: ").append(toIndentedString(registeredCapital)).append("\n");
        sb.append("    password: ").append(toIndentedString(legalPerson)).append("\n");
        sb.append("    username: ").append(toIndentedString(organizationCode)).append("\n");
        sb.append("    business: ").append(toIndentedString(business)).append("\n");
        sb.append("    enterpriseDetail: ").append(toIndentedString(enterpriseDetail)).append("\n");
        sb.append("    email: ").append(toIndentedString(info)).append("\n");
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

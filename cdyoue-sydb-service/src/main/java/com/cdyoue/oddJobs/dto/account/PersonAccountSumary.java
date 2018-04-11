package com.cdyoue.oddJobs.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/5/12.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class PersonAccountSumary {
    @JsonProperty("headImg")//头像--
    private String headImg;
    @JsonProperty("name")//姓名--
    private String name;
    @JsonProperty("nickName")//昵称--
    private String nickName;

    @JsonProperty("sex")//性别
    private Integer sex;
    @JsonProperty("homeTown")//家乡--
    private String homeTown;
    @JsonProperty("location")//居住地--
    private String location;
    @JsonProperty("expectFunctionCategory")//所在行业
    private Integer expectFunctionCategory;
    @JsonProperty("email")//邮箱
    private String email;
    @JsonProperty("tel")//电话
    private String tel;
    @JsonProperty("info")//简介--
    private String info;
    @JsonProperty("introduction")//详细介绍--
    private String introduction;
    @JsonProperty("recruitmentCategoryId")//求职意向id--
    private Integer recruitmentCategoryId;
    @JsonProperty("parttimeCategoryId")//兼职意向id--
    private Integer parttimeCategoryId;
    @JsonProperty("outsourcingprojectType")//外包意向id--
    private Integer outsourcingprojectType;
    @JsonProperty("age")
    private Integer age;

    @JsonProperty("workingLife")
    private Integer workingLife;


    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Integer getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(final Integer workingLife) {
        this.workingLife = workingLife;
    }

    /**
     * 姓名
     * @return name
     **/
    @ApiModelProperty(example = "叶良辰", value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 性别
     * @return sex
     **/
    @ApiModelProperty(example = "0", value = "性别:0男，1女，2其他")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    /**
     * 头像
     * @return headImg
     **/
    @ApiModelProperty(example = "/xxx/xxx/xxx.jpg", value = "头像")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    /**
     * 家乡
     * @return homeTown
     **/
    @ApiModelProperty(example = "新疆", value = "家乡")
    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    /**
     * 居住地
     * @return location
     **/
    @ApiModelProperty(example = "成都", value = "居住地")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * 个人简介
     * @return info
     **/
    @ApiModelProperty(example = "没有我良辰摆不平的事儿", value = "个人简介")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 详细介绍
     * @return introduction
     **/
    @ApiModelProperty(example = "详细介绍", value = "详细介绍")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }




    /**
     * 所在行业
     * @return expectFunctionCategory
     **/
    @ApiModelProperty(example = "1", value = "所在行业")
    public Integer getExpectFunctionCategory() {
        return expectFunctionCategory;
    }

    public void setExpectFunctionCategory(Integer expectFunctionCategory) {
        this.expectFunctionCategory = expectFunctionCategory;
    }

    /**
     * 邮箱
     * @return email
     **/
    @ApiModelProperty(example = "dmksdg@qq.com", value = "邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 电话
     * @return tel
     **/
    @ApiModelProperty(example = "151****0067", value = "电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 求职意向
     * @return recruitmentCategoryId
     **/
    @ApiModelProperty(example = "1", value = "求职意向ID")
    public Integer getRecruitmentCategoryId() {
        return recruitmentCategoryId;
    }

    public void setRecruitmentCategoryId(Integer recruitmentCategoryId) {
        this.recruitmentCategoryId = recruitmentCategoryId;
    }

    /**
     * 兼职意向
     * @return parttimeCategoryId
     **/
    @ApiModelProperty(example = "1", value = "兼职意向ID")
    public Integer getParttimeCategoryId() {
        return parttimeCategoryId;
    }

    public void setParttimeCategoryId(Integer parttimeCategoryId) {
        this.parttimeCategoryId = parttimeCategoryId;
    }

    /**
     * 外包意向
     * @return outsourcingprojectType
     **/
    @ApiModelProperty(example = "1", value = "外包意向ID")
    public Integer getOutsourcingprojectType() {
        return outsourcingprojectType;
    }

    public void setOutsourcingprojectType(Integer outsourcingprojectType) {
        this.outsourcingprojectType = outsourcingprojectType;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }
}

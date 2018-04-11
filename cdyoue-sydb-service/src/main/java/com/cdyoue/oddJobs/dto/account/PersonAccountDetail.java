package com.cdyoue.oddJobs.dto.account;


import com.cdyoue.oddJobs.dto.oauth.RoleSumary;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-08T12:12:19.725Z")
public class PersonAccountDetail {
    @JsonProperty("userId")//用户ID
    private int userId;
    @JsonProperty("headImg")//头像
    private String headImg;
    @JsonProperty("name")//姓名
    private String name;
    @JsonProperty("nickName")//昵称
    private String nickName;
    @JsonProperty("job")//工作
    private String job;
    @JsonProperty("sex")//性别
    private Integer sex;
    @JsonProperty("age")//年龄
    private Integer age;
    @JsonProperty("email")//邮箱
    private String email;
    @JsonProperty("tel")//电话
    private String tel;
    @JsonProperty("homeTown")//家乡
    private String homeTown;
    @JsonProperty("location")//居住地
    private String location;
    @JsonProperty("workingLife")//工龄
    private Integer workingLife;
    @JsonProperty("expectFunctionCategory")//所在行业
    private Integer expectFunctionCategory;
    private String expectFunctionCategoryInfo;
    @JsonProperty("info")//简介
    private String info;
    @JsonProperty("introduction")//详细介绍
    private String introduction;
    @JsonProperty("expertType")//用户认证类型
    private List<Integer> expertType;
    @JsonProperty("integrityDegree")//资料完整度
    private BigDecimal integrityDegree;
    @JsonProperty("recruitmentCategoryId")//求职意向id
    private Integer recruitmentCategoryId;
    private String recruitmentCategoryInfo;
    @JsonProperty("parttimeCategoryId")//兼职意向id
    private Integer parttimeCategoryId;
    private String parttimeCategoryInfo;
    @JsonProperty("outsourcingprojectType")//外包意向id
    private Integer outsourcingprojectType;
    private String outsourcingprojectInfo;
    @JsonProperty("role")//用戶角色
    private Integer role;
    @JsonProperty("viewCount")
    private Integer viewCount;
    @JsonProperty("attentionNum")
    private Integer attentionNum;

    @JsonProperty("menuRolesName")
    private List<String> menuRolesName;


    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(final Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(final Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getRecruitmentCategoryInfo() {
        return recruitmentCategoryInfo;
    }

    public void setRecruitmentCategoryInfo(String recruitmentCategoryInfo) {
        this.recruitmentCategoryInfo = recruitmentCategoryInfo;
    }

    public String getParttimeCategoryInfo() {
        return parttimeCategoryInfo;
    }

    public void setParttimeCategoryInfo(String parttimeCategoryInfo) {
        this.parttimeCategoryInfo = parttimeCategoryInfo;
    }

    public String getOutsourcingprojectInfo() {
        return outsourcingprojectInfo;
    }

    public void setOutsourcingprojectInfo(String outsourcingprojectInfo) {
        this.outsourcingprojectInfo = outsourcingprojectInfo;
    }

    public String getExpectFunctionCategoryInfo() {
        return expectFunctionCategoryInfo;
    }

    public void setExpectFunctionCategoryInfo(final String expectFunctionCategoryInfo) {
        this.expectFunctionCategoryInfo = expectFunctionCategoryInfo;
    }

    /**
     * 用户ID
     *
     * @return userId
     **/
    @ApiModelProperty(example = "1", value = "用户ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 姓名
     *
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
     * 昵称
     *
     * @return
     */
    @ApiModelProperty(example = "昵称", value = "昵称")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 性别
     *
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
     * 年龄
     *
     * @return age
     **/
    @ApiModelProperty(example = "20", value = "年龄")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 头像
     *
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
     * 职务
     *
     * @return job
     **/
    @ApiModelProperty(example = "技术总监", value = "职务")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 家乡
     *
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
     *
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
     *
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
     *
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
     * 专家类型
     *
     * @return expertType
     **/
    @ApiModelProperty(example = "1", value = "认证类型：1实名，2大咖，3导师")
    public List<Integer> getExpertType() {
        return expertType;
    }

    public void setExpertType(List<Integer> expertType) {
        this.expertType = expertType;
    }

    /**
     * 资料完整度
     *
     * @return name
     **/
    @ApiModelProperty(example = "", value = "资料完整度")
    public BigDecimal getIntegrityDegree() {
        return integrityDegree;
    }

    public void setIntegrityDegree(BigDecimal integrityDegree) {
        this.integrityDegree = integrityDegree;
    }

    /**
     * 工作年限
     *
     * @return workingLife
     **/
    @ApiModelProperty(example = "15", value = "工作年限")
    public Integer getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(Integer workingLife) {
        this.workingLife = workingLife;
    }

    /**
     * 所在行业
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @return outsourcingprojectType
     **/
    @ApiModelProperty(example = "1", value = "外包意向ID")
    public Integer getOutsourcingprojectType() {
        return outsourcingprojectType;
    }

    public void setOutsourcingprojectType(Integer outsourcingprojectType) {
        this.outsourcingprojectType = outsourcingprojectType;
    }

    /**
     * 用戶角色
     *
     * @return outsourcingprojectType
     **/
    @ApiModelProperty(example = "1", value = "用戶角色：0个人用户 1 企业用户 2超级管理员")
    public Integer getRole() {
        return role;
    }

    @ApiModelProperty(example = "注册权限", value = "菜单权限角色名")
    public List<String> getMenuRolesName() {
        return menuRolesName;
    }

    public void setMenuRolesName(List<String> menuRolesName) {
        this.menuRolesName = menuRolesName;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}

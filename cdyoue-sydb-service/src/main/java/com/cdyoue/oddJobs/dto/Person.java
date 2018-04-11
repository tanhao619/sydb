package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Person
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class Person   {
  @JsonProperty("age")
  private Integer age = null;

  @JsonProperty("sex")
  private String sex = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("company")
  private String company = null;

  @JsonProperty("experience")
  private String experience = null;

  @JsonProperty("degree")
  private String degree = null;

  @JsonProperty("skill")
  private String skill = null;

  @JsonProperty("industry")
  private String industry = null;

  @JsonProperty("position")
  private String position = null;

  @JsonProperty("district")
  private String district = null;

  @JsonProperty("loginCount")
  private Integer loginCount = null;

  @JsonProperty("activityCount")
  private Integer activityCount = null;

  @JsonProperty("firstLoginTime")
  private String firstLoginTime = null;

  @JsonProperty("lastLoginTime")
  private String lastLoginTime = null;

  @JsonProperty("legends")
  private List<Legend> legends = new ArrayList<Legend>();

  public Person age(Integer age) {
    this.age = age;
    return this;
  }

   /**
   * 年龄
   * @return age
  **/
  @ApiModelProperty(example = "38", value = "年龄")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Person sex(String sex) {
    this.sex = sex;
    return this;
  }

   /**
   * 性别
   * @return sex
  **/
  @ApiModelProperty(example = "男", value = "性别")
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Person phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * 电话
   * @return phone
  **/
  @ApiModelProperty(example = "13800000000", value = "电话")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Person company(String company) {
    this.company = company;
    return this;
  }

   /**
   * 公司
   * @return company
  **/
  @ApiModelProperty(example = "国信优易", value = "公司")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public Person experience(String experience) {
    this.experience = experience;
    return this;
  }

   /**
   * 工作经验
   * @return experience
  **/
  @ApiModelProperty(example = "xxxx", value = "工作经验")
  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public Person degree(String degree) {
    this.degree = degree;
    return this;
  }

   /**
   * 学历
   * @return degree
  **/
  @ApiModelProperty(example = "硕士", value = "学历")
  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public Person skill(String skill) {
    this.skill = skill;
    return this;
  }

   /**
   * 年龄
   * @return skill
  **/
  @ApiModelProperty(example = "编程", value = "年龄")
  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public Person industry(String industry) {
    this.industry = industry;
    return this;
  }

   /**
   * 所属行业
   * @return industry
  **/
  @ApiModelProperty(example = "IT", value = "所属行业")
  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public Person position(String position) {
    this.position = position;
    return this;
  }

   /**
   * 所属职位
   * @return position
  **/
  @ApiModelProperty(example = "初级程序员", value = "所属职位")
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Person district(String district) {
    this.district = district;
    return this;
  }

   /**
   * 地区
   * @return district
  **/
  @ApiModelProperty(example = "四川-成都", value = "地区")
  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public Person loginCount(Integer loginCount) {
    this.loginCount = loginCount;
    return this;
  }

   /**
   * 登录次数
   * @return loginCount
  **/
  @ApiModelProperty(example = "888", value = "登录次数")
  public Integer getLoginCount() {
    return loginCount;
  }

  public void setLoginCount(Integer loginCount) {
    this.loginCount = loginCount;
  }

  public Person activityCount(Integer activityCount) {
    this.activityCount = activityCount;
    return this;
  }

   /**
   * 活跃度
   * @return activityCount
  **/
  @ApiModelProperty(example = "888", value = "活跃度")
  public Integer getActivityCount() {
    return activityCount;
  }

  public void setActivityCount(Integer activityCount) {
    this.activityCount = activityCount;
  }

  public Person firstLoginTime(String firstLoginTime) {
    this.firstLoginTime = firstLoginTime;
    return this;
  }

   /**
   * 第一次登录
   * @return firstLoginTime
  **/
  @ApiModelProperty(example = "2017/4/13 21:52", value = "第一次登录")
  public String getFirstLoginTime() {
    return firstLoginTime;
  }

  public void setFirstLoginTime(String firstLoginTime) {
    this.firstLoginTime = firstLoginTime;
  }

  public Person lastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
    return this;
  }

   /**
   * 最后一次登录
   * @return lastLoginTime
  **/
  @ApiModelProperty(example = "2017/4/19 21:52", value = "最后一次登录")
  public String getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Person legends(List<Legend> legends) {
    this.legends = legends;
    return this;
  }

  public Person addLegendsItem(Legend legendsItem) {
    this.legends.add(legendsItem);
    return this;
  }

   /**
   * 图例分类
   * @return legends
  **/
  @ApiModelProperty(example = "[&quot;大数据&quot;,&quot;电商&quot;,&quot;互联网&quot;,&quot;视频广告&quot;,&quot;搜索引擎&quot;]", value = "图例分类")
  public List<Legend> getLegends() {
    return legends;
  }

  public void setLegends(List<Legend> legends) {
    this.legends = legends;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(this.age, person.age) &&
        Objects.equals(this.sex, person.sex) &&
        Objects.equals(this.phone, person.phone) &&
        Objects.equals(this.company, person.company) &&
        Objects.equals(this.experience, person.experience) &&
        Objects.equals(this.degree, person.degree) &&
        Objects.equals(this.skill, person.skill) &&
        Objects.equals(this.industry, person.industry) &&
        Objects.equals(this.position, person.position) &&
        Objects.equals(this.district, person.district) &&
        Objects.equals(this.loginCount, person.loginCount) &&
        Objects.equals(this.activityCount, person.activityCount) &&
        Objects.equals(this.firstLoginTime, person.firstLoginTime) &&
        Objects.equals(this.lastLoginTime, person.lastLoginTime) &&
        Objects.equals(this.legends, person.legends);
  }

  @Override
  public int hashCode() {
    return Objects.hash(age, sex, phone, company, experience, degree, skill, industry, position, district, loginCount, activityCount, firstLoginTime, lastLoginTime, legends);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Person {\n");
    
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    experience: ").append(toIndentedString(experience)).append("\n");
    sb.append("    degree: ").append(toIndentedString(degree)).append("\n");
    sb.append("    skill: ").append(toIndentedString(skill)).append("\n");
    sb.append("    industry: ").append(toIndentedString(industry)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    district: ").append(toIndentedString(district)).append("\n");
    sb.append("    loginCount: ").append(toIndentedString(loginCount)).append("\n");
    sb.append("    activityCount: ").append(toIndentedString(activityCount)).append("\n");
    sb.append("    firstLoginTime: ").append(toIndentedString(firstLoginTime)).append("\n");
    sb.append("    lastLoginTime: ").append(toIndentedString(lastLoginTime)).append("\n");
    sb.append("    legends: ").append(toIndentedString(legends)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


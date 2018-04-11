package com.cdyoue.oddJobs.dto.lgfc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Objects;
/**
 * TalentSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")

public class TalentSummary   {
  @JsonProperty("talentBase")
  private TalentBase talentBase = null;

  @JsonProperty("contact")
  private String contact = null;

  @JsonProperty("industry")
  private String industry = null;

  @JsonProperty("position")
  private String position = null;

  @JsonProperty("sentence")
  private String sentence = null;

  @JsonProperty("introduction")
  private String introduction = null;

  @JsonProperty("dataComp")
  private Integer dataComp = null;

  @JsonProperty("invitedNum")
  private Integer invitedNum = null;

  @JsonProperty("age")
  private Integer age = null;

  @JsonProperty("sex")
  private Integer sex = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("experienceName")
  private String experienceName = null;

  @JsonProperty("major")
  private String major = null;

  @JsonProperty("education")
  private Byte education = null;

  @JsonProperty("startTime")
  private Timestamp startTime = null;

  @JsonProperty("endTime")
  private Timestamp endTime = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("WorkingLife")
  private Integer WorkingLife = null;

  public TalentSummary talentBase(TalentBase talentBase) {
    this.talentBase = talentBase;
    return this;
  }

   /**
   * Get talentBase
   * @return talentBase
  **/
  @ApiModelProperty(value = "")
  public TalentBase getTalentBase() {
    return talentBase;
  }

  public void setTalentBase(TalentBase talentBase) {
    this.talentBase = talentBase;
  }

  public TalentSummary contact(String contact) {
    this.contact = contact;
    return this;
  }

   /**
   * 联系方式
   * @return contact
  **/
  @ApiModelProperty(example = "1380000000", value = "联系方式")
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public TalentSummary industry(String industry) {
    this.industry = industry;
    return this;
  }

   /**
   * 所在行业
   * @return industry
  **/
  @ApiModelProperty(example = "IT", value = "所在行业")
  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public TalentSummary position(String position) {
    this.position = position;
    return this;
  }

   /**
   * 职位
   * @return position
  **/
  @ApiModelProperty(example = "程序员", value = "职位")
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public TalentSummary sentence(String sentence) {
    this.sentence = sentence;
    return this;
  }

   /**
   * 一句话介绍
   * @return sentence
  **/
  @ApiModelProperty(example = "我是程序员", value = "签名")
  public String getSentence() {
    return sentence;
  }

  public void setSentence(String sentence) {
    this.sentence = sentence;
  }

  public TalentSummary introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

   /**
   * 个人简介
   * @return introduction
  **/
  @ApiModelProperty(example = "2013年5月10日，辞任阿里巴巴集团CEO，继续担任阿里集团董事局主席。", value = "个人简介")
  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public TalentSummary dataComp(Integer dataComp) {
    this.dataComp = dataComp;
    return this;
  }

   /**
   * 资料完整度
   * @return dataComp
  **/
  @ApiModelProperty(example = "98", value = "资料完整度")
  public Integer getDataComp() {
    return dataComp;
  }

  public void setDataComp(Integer dataComp) {
    this.dataComp = dataComp;
  }

  public TalentSummary invitedNum(Integer invitedNum) {
    this.invitedNum = invitedNum;
    return this;
  }

   /**
   * 被邀请数
   * @return invitedNum
  **/
  @ApiModelProperty(example = "我是程序员", value = "被邀请数")
  public Integer getInvitedNum() {
    return invitedNum;
  }

  public void setInvitedNum(Integer invitedNum) {
    this.invitedNum = invitedNum;
  }

  public TalentSummary age(Integer age) {
    this.age = age;
    return this;
  }

   /**
   * 年龄
   * @return age
  **/
  @ApiModelProperty(example = "25", value = "年龄")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public TalentSummary sex(Integer sex) {
    this.sex = sex;
    return this;
  }

   /**
   * Get sex
   * @return sex
  **/
  @ApiModelProperty(value = "")
  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public TalentSummary country(String country) {
    this.country = country;
    return this;
  }

   /**
   * 家乡
   * @return country
  **/
  @ApiModelProperty(example = "2013年5月10日，辞任阿里巴巴集团CEO，继续担任阿里集团董事局主席。", value = "家乡")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public TalentSummary location(String location) {
    this.location = location;
    return this;
  }

   /**
   * 现居地
   * @return location
  **/
  @ApiModelProperty(example = "北京·丰台", value = "现居地")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public TalentSummary experienceName(String experienceName) {
    this.experienceName = experienceName;
    return this;
  }
  /**
   * 技能名称
   * @return experienceName
   **/
  @ApiModelProperty(example = "国信优易数据有限公司", value = "技能名称")
  public String getExperienceName() {
    return experienceName;
  }
  public void setExperienceName(String experienceName) {
    this.experienceName = experienceName;
  }

  public TalentSummary major(String major) {
    this.major = major;
    return this;
  }
  /**
   * 专业（教育行业）、职位（职业经历）
   * @return major
   **/
  @ApiModelProperty(example = "教育学", value = "专业（教育行业）、职位（职业经历）")
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }

  public TalentSummary education(Byte education) {
    this.education = education;
    return this;
  }
  /**
   * 学历
   * @return education
   **/
  @ApiModelProperty(example = "本科", value = "学历")
  public Byte getEducation() {
    return education;
  }
  public void setEducation(Byte education) {
    this.education = education;
  }

  public TalentSummary startTime(Timestamp startTime) {
    this.startTime = startTime;
    return this;
  }
  /**
   * 开始时间
   * @return startTime
   **/
  @ApiModelProperty(example = "", value = "开始时间")
  public Timestamp getStartTime() {
    return startTime;
  }
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public TalentSummary endTime(Timestamp endTime) {
    this.endTime = endTime;
    return this;
  }
  /**
   * 结束时间
   * @return endTime
   **/
  @ApiModelProperty(example = "", value = "结束时间")
  public Timestamp getEndTime() {
    return endTime;
  }
  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public TalentSummary info(String info) {
    this.info = info;
    return this;
  }
  /**
   * 简介
   * @return info
   **/
  @ApiModelProperty(example = "", value = "简介")
  public String getInfo() {
    return info;
  }
  public void setInfo(String info) {
    this.info = info;
  }

  public TalentSummary WorkingLife(Integer WorkingLife) {
    this.WorkingLife = WorkingLife;
    return this;
  }
  /**
   * 工作经验
   * @return WorkingLife
   **/
  @ApiModelProperty(example = "", value = "工作经验")
  public Integer getWorkingLife() {
    return WorkingLife;
  }
  public void setWorkingLife(Integer WorkingLife) {
    this.WorkingLife = WorkingLife;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TalentSummary talentSummary = (TalentSummary) o;
    return Objects.equals(this.talentBase, talentSummary.talentBase) &&
        Objects.equals(this.contact, talentSummary.contact) &&
        Objects.equals(this.industry, talentSummary.industry) &&
        Objects.equals(this.position, talentSummary.position) &&
        Objects.equals(this.sentence, talentSummary.sentence) &&
        Objects.equals(this.introduction, talentSummary.introduction) &&
        Objects.equals(this.dataComp, talentSummary.dataComp) &&
        Objects.equals(this.invitedNum, talentSummary.invitedNum) &&
        Objects.equals(this.age, talentSummary.age) &&
        Objects.equals(this.sex, talentSummary.sex) &&
        Objects.equals(this.country, talentSummary.country) &&
        Objects.equals(this.location, talentSummary.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(talentBase, contact, industry, position, sentence, introduction, dataComp, invitedNum, age, sex, country, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TalentSummary {\n");
    
    sb.append("    talentBase: ").append(toIndentedString(talentBase)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    industry: ").append(toIndentedString(industry)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    sentence: ").append(toIndentedString(sentence)).append("\n");
    sb.append("    introduction: ").append(toIndentedString(introduction)).append("\n");
    sb.append("    dataComp: ").append(toIndentedString(dataComp)).append("\n");
    sb.append("    invitedNum: ").append(toIndentedString(invitedNum)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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


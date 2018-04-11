package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * InvestorDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T01:11:39.286Z")

public class InvestorDetail   {

  @JsonProperty("name")
  private String name;

  @JsonProperty("titles")
  private List<String> titles = null;

  @JsonProperty("domains")
  private List<Domain> domains = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("orgName")
  private String orgName = null;

  @JsonProperty("orgImg")
  private String orgImg = null;

  @JsonProperty("tel")
  private String tel = null;

  @JsonProperty("orgLink")
  private String orgLink = null;

  @JsonProperty("headImg")
  private String headImg = null;

    @ApiModelProperty(example = "18681221458", value = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ApiModelProperty(example = "tangrui", value = "投资人姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public InvestorDetail titles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public InvestorDetail addTitlesItem(String titlesItem) {
        this.titles.add(titlesItem);
        return this;
    }

    /**
     * Get titles
     * @return titles
     **/
    @ApiModelProperty(value = "")
    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
    public InvestorDetail domains(List<Domain> domains) {
        this.domains = domains;
        return this;
    }

    public InvestorDetail addDomainsItem(Domain domainsItem) {
        this.domains.add(domainsItem);
        return this;
    }

    /**
     * Get domains
     * @return domains
     **/
    @ApiModelProperty(value = "")
    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    @ApiModelProperty(example = "rt4a14@soton.ac.uk", value = "联系邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(example = "先投资个一个亿", value = "详细内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(example = "天上人间会所", value = "所属机构")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    @ApiModelProperty(example = "www.youedata.com/i/a.jpg", value = "机构Logo")
    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg;
    }

    @ApiModelProperty(example = "www.youedata.com", value = "机构链接")
    public String getOrgLink() {
        return orgLink;
    }

    public void setOrgLink(String orgLink) {
        this.orgLink = orgLink;
    }

    @ApiModelProperty(example = "www.youedata.com/a//investor.1.jpg", value = "投资人头像")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvestorDetail that = (InvestorDetail) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (titles != null ? !titles.equals(that.titles) : that.titles != null) return false;
        if (domains != null ? !domains.equals(that.domains) : that.domains != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (orgImg != null ? !orgImg.equals(that.orgImg) : that.orgImg != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (orgLink != null ? !orgLink.equals(that.orgLink) : that.orgLink != null) return false;
        if (headImg != null ? !headImg.equals(that.headImg) : that.headImg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (titles != null ? titles.hashCode() : 0);
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgImg != null ? orgImg.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (orgLink != null ? orgLink.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        return result;
    }
}


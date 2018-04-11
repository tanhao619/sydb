package com.cdyoue.oddJobs.dto.scfw;

import com.cdyoue.oddJobs.dto.Contact;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * InvestorSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-16T01:11:39.286Z")

public class InvestorSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("contact")
  private Contact contact = null;

  @JsonProperty("investor")
  private Investor investor = null;

  @JsonProperty("viewCount")
  private Integer viewCount = null;

  @JsonProperty("createBy")
  private String createBy = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("organization")
  private Organization organization = null;

  @JsonProperty("domains")
  private List<Domain> domains = new ArrayList<Domain>();

  public InvestorSummary id(Integer id) {
    this.id = id;
    return this;
  }

    @ApiModelProperty(example = "8888", value = "浏览量")
    public Integer getViewCount() {
        return viewCount == null ? 0 : viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @ApiModelProperty(example = "tr", value = "发布人")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @ApiModelProperty(example = "2012-02-34 12:00:12", value = "发布时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
   * 机构id
    * @return id
    **/
  @ApiModelProperty(example = "12333", value = "主键id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public InvestorSummary contact(Contact contact) {
    this.contact = contact;
    return this;
  }

   /**
   * Get contact
   * @return contact
  **/
  @ApiModelProperty(value = "")
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public InvestorSummary investor(Investor investor) {
    this.investor = investor;
    return this;
  }

   /**
   * Get investor
   * @return investor
  **/
  @ApiModelProperty(value = "")
  public Investor getInvestor() {
    return investor;
  }

  public void setInvestor(Investor investor) {
    this.investor = investor;
  }

  public InvestorSummary organization(Organization organization) {
    this.organization = organization;
    return this;
  }

   /**
   * Get organization
   * @return organization
  **/
  @ApiModelProperty(value = "")
  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public InvestorSummary domains(List<Domain> domains) {
    this.domains = domains;
    return this;
  }

  public InvestorSummary addDomainsItem(Domain domainsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvestorSummary investorSummary = (InvestorSummary) o;
    return Objects.equals(this.id, investorSummary.id) &&
        Objects.equals(this.contact, investorSummary.contact) &&
        Objects.equals(this.investor, investorSummary.investor) &&
        Objects.equals(this.organization, investorSummary.organization) &&
        Objects.equals(this.domains, investorSummary.domains);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contact, investor, organization, domains);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvestorSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    investor: ").append(toIndentedString(investor)).append("\n");
    sb.append("    organization: ").append(toIndentedString(organization)).append("\n");
    sb.append("    domains: ").append(toIndentedString(domains)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


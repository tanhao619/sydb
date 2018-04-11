package com.cdyoue.oddJobs.dto.scfw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
/**
 * Organization
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T00:59:32.305Z")

public class Organization   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("logoUrl")
  private String logoUrl = null;

  @JsonProperty("orgLink")
  private String orgLink = null;

  public Organization name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 机构名
   * @return name
  **/
  @ApiModelProperty(example = "IBM", value = "机构名")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Organization logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

   /**
   * 封面
   * @return logoUrl
  **/
  @ApiModelProperty(example = "http://172.16.0.102/image.jpg", value = "封面")
  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public Organization orgLink(String orgLink) {
        this.orgLink = orgLink;
        return this;
    }
    /**
     * 机构链接
     * @return orgLink
     **/
    @ApiModelProperty(example = "http://www.southampton.ac.uk", value = "机构链接")
    public String getOrgLink() {
        return orgLink;
    }

    public void setOrgLink(String orgLink) {
        this.orgLink = orgLink;
    }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organization organization = (Organization) o;
    return Objects.equals(this.name, organization.name) &&
        Objects.equals(this.logoUrl, organization.logoUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, logoUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Organization {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
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


package com.cdyoue.oddJobs.dto.requirement;

import com.cdyoue.oddJobs.dto.EmployerInfo;
import com.cdyoue.oddJobs.dto.lgfc.TalentSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求复合信息，包括需求企业信息，需求详细信息和需求承接人信息
 */
@ApiModel(description = "需求复合信息，包括需求企业信息，需求详细信息和需求承接人信息")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

public class RequireComp   {
  @JsonProperty("employerInfo")
  private EmployerInfo employerInfo = null;

  @JsonProperty("requireInfo")
  private RequireDetails requireInfo = null;

  @JsonProperty("acceptPeoples")
  private List<TalentSummary> acceptPeoples = new ArrayList<TalentSummary>();


  @ApiModelProperty("雇主信息 访问者不为雇主时呈现")
  public EmployerInfo getEmployerInfo() {
    return employerInfo;
  }

  public void setEmployerInfo(EmployerInfo userDetailInfo) {
    this.employerInfo = userDetailInfo;
  }
  @ApiModelProperty("需求信息")
  public RequireDetails getRequireInfo() {
    return requireInfo;
  }

  public void setRequireInfo(RequireDetails requireInfo) {
    this.requireInfo = requireInfo;
  }
  @ApiModelProperty("已承接需求人员列表 访问者为雇主时呈现")
  public List<TalentSummary> getAcceptPeoples() {
    return acceptPeoples;
  }


  public void setAcceptPeoples(List<TalentSummary> acceptPeoples) {
    this.acceptPeoples = acceptPeoples;
  }
}


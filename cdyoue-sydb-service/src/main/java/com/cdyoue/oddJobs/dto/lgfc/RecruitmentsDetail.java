package com.cdyoue.oddJobs.dto.lgfc;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecruitmentsDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsDetail   {
  @JsonProperty("RecruitmentsPartMini")
  private RecruitmentsPartMini recruitmentsPartMini = null;
  @JsonProperty("RecruitmentsFullMini")
  private RecruitmentsFullMini recruitmentsFullMini = null;
  @JsonProperty("acceptPeoples")
  private List<Talent4Work> acceptPeoples = new ArrayList<Talent4Work>();
  @JsonProperty("enterprise")
  private String enterprise = null;

  public RecruitmentsPartMini getRecruitmentsPartMini() {
    return recruitmentsPartMini;
  }

  public void setRecruitmentsPartMini(RecruitmentsPartMini recruitmentsPartMini) {
    this.recruitmentsPartMini = recruitmentsPartMini;
  }

  public RecruitmentsFullMini getRecruitmentsFullMini() {
    return recruitmentsFullMini;
  }

  public void setRecruitmentsFullMini(RecruitmentsFullMini recruitmentsFullMini) {
    this.recruitmentsFullMini = recruitmentsFullMini;
  }

  public String getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(String enterprise) {
    this.enterprise = enterprise;
  }

  @Override
  public String toString() {
    return "RecruitmentsDetail{" +
            "recruitmentsPartMini=" + recruitmentsPartMini +
            ", recruitmentsFullMini=" + recruitmentsFullMini +
            ", enterprise='" + enterprise + '\'' +
            '}';
  }
  @ApiModelProperty("已应聘人员列表 访问者为雇主时呈现")
  public List<Talent4Work> getAcceptPeoples() {
    return acceptPeoples;
  }


  public void setAcceptPeoples(List<Talent4Work> acceptPeoples) {
    this.acceptPeoples = acceptPeoples;
  }
}


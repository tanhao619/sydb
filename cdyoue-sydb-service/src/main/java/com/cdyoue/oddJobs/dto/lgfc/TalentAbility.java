package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * TalentAbility
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-04T01:20:49.378Z")

public class TalentAbility   {
  @JsonProperty("talentSummary")
  private TalentSummary talentSummary = null;

  @JsonProperty("tags")
  private List<String> tags = new ArrayList<String>();

  public TalentAbility talentSummary(TalentSummary talentSummary) {
    this.talentSummary = talentSummary;
    return this;
  }

   /**
   * Get talentSummary
   * @return talentSummary
  **/
  @ApiModelProperty(value = "")
  public TalentSummary getTalentSummary() {
    return talentSummary;
  }

  public void setTalentSummary(TalentSummary talentSummary) {
    this.talentSummary = talentSummary;
  }

  public TalentAbility tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public TalentAbility addTagsItem(String tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * 标签
   * @return tags
  **/
  @ApiModelProperty(value = "标签")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TalentAbility talentAbility = (TalentAbility) o;
    return Objects.equals(this.talentSummary, talentAbility.talentSummary) &&
        Objects.equals(this.tags, talentAbility.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(talentSummary, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TalentAbility {\n");
    
    sb.append("    talentSummary: ").append(toIndentedString(talentSummary)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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


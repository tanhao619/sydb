package com.cdyoue.oddJobs.dto.zlcx;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by sky on 2017/9/18.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class DeclarationPeopleSummary {
    @JsonProperty("projectId")
    private Integer projectId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("phoneNumber")
    private String phoneNumber = null;

    @JsonProperty("attachmentUrl")
    private String attachmentUrl = null;

    public DeclarationPeopleSummary projectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }
    /**
     * 项目id
     * @return department
     **/
    @ApiModelProperty(example = "1", value = "项目id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public DeclarationPeopleSummary name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 申报人
     * @return department
     **/
    @ApiModelProperty(example = "马云", value = "申报人")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeclarationPeopleSummary phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * 联系电话
     * @return department
     **/
    @ApiModelProperty(example = "110121010101", value = "联系电话")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    /**
     * 附件id
     * @return department
     **/
    @ApiModelProperty(example = "1", value = "附件id")
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public DeclarationPeopleSummary attachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeclarationPeopleSummary declarationPeopleSummary = (DeclarationPeopleSummary) o;
        return Objects.equals(this.phoneNumber, declarationPeopleSummary.phoneNumber) &&
                Objects.equals(this.attachmentUrl, declarationPeopleSummary.attachmentUrl) &&
                Objects.equals(this.projectId, declarationPeopleSummary.projectId) &&
                Objects.equals(this.name, declarationPeopleSummary.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, attachmentUrl, name,projectId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectSummary {\n");

        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    attachmentId: ").append(toIndentedString(attachmentUrl)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
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

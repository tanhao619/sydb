package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecruitmentsPartInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsPartInfo {
    @JsonProperty("recruitmentsInfo")
    private RecruitmentsInfo recruitmentsInfo = null;

    @JsonProperty("expectedSalary")
    private Integer expectedSalary = null;

    @JsonProperty("workTime")
    private String workTime = null;

    public RecruitmentsPartInfo recruitmentsInfo(RecruitmentsInfo recruitmentsInfo) {
        this.recruitmentsInfo = recruitmentsInfo;
        return this;
    }

    /**
     * Get recruitmentsInfo
     *
     * @return recruitmentsInfo
     **/
    @ApiModelProperty(value = "")
    public RecruitmentsInfo getRecruitmentsInfo() {
        return recruitmentsInfo;
    }

    public void setRecruitmentsInfo(RecruitmentsInfo recruitmentsInfo) {
        this.recruitmentsInfo = recruitmentsInfo;
    }

    public RecruitmentsPartInfo salary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
        return this;
    }


    /**
     * 薪资待遇
     *
     * @return salary
     **/
    @ApiModelProperty(example = "20000", value = "薪资待遇")
    public Integer getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecruitmentsPartInfo recruitmentsPartInfo = (RecruitmentsPartInfo) o;
        return Objects.equals(this.recruitmentsInfo, recruitmentsPartInfo.recruitmentsInfo) &&
                Objects.equals(this.expectedSalary, recruitmentsPartInfo.expectedSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recruitmentsInfo, expectedSalary);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RecruitmentsPartInfo {\n");

        sb.append("    recruitmentsBase: ").append(toIndentedString(recruitmentsInfo)).append("\n");
        sb.append("    salary: ").append(toIndentedString(expectedSalary)).append("\n");
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

    @ApiModelProperty(example = "2016-12-19 16:46-18:00", value = "工作时间")
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}


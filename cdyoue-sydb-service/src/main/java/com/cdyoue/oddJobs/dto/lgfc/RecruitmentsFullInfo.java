package com.cdyoue.oddJobs.dto.lgfc;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecruitmentsFullInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-06T22:58:08.241Z")

public class RecruitmentsFullInfo {

    @JsonProperty("recruitmentsInfo")
    private RecruitmentsInfo recruitmentsInfo = null;

    @JsonProperty("welfare")
    private String welfare = null;

    @JsonProperty("educationalBackground")
    private Integer educationalBackground = null;

    @JsonProperty("workingLife")
    private Integer workingLife = null;

    @JsonProperty("expectedSalary")
    private Integer expectedSalary = null;


    public RecruitmentsFullInfo recruitmentsInfo(RecruitmentsInfo recruitmentsInfo) {
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


    public RecruitmentsFullInfo welfare(String welfare) {
        this.welfare = welfare;
        return this;
    }
    /**
     * 公司福利
     *
     * @return welfare
     **/
    @ApiModelProperty(example = "五险一金", value = "公司福利")
    public String getWelfare() {
        return welfare;
    }
    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }


    public RecruitmentsFullInfo educationalBackground(Integer educationalBackground) {
        this.educationalBackground = educationalBackground;
        return this;
    }
    /**
     * 学历要求
     *
     * @return educationalBackground
     **/
    @ApiModelProperty(example = "3", value = "学历要求（1：高中/中专；2：大专；3：本科；4：硕士；5：博士；6：博士后）")
    public Integer getEducationalBackground() {
        return educationalBackground;
    }
    public void setEducationalBackground(Integer educationalBackground) {
        this.educationalBackground = educationalBackground;
    }


    public RecruitmentsFullInfo workingLife(Integer workingLife) {
        this.workingLife = workingLife;
        return this;
    }
    /**
     * 经验要求
     * @return workingLife
     */
    @ApiModelProperty(example = "1", value = "经验要求:1、0-1,2、1-3,3、3-5,4、5-10,5、10以上")
    public Integer getWorkingLife() {
        return workingLife;
    }
    public void setWorkingLife(Integer workingLife) {
        this.workingLife = workingLife;
    }

    public RecruitmentsFullInfo expectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
        return this;
    }
    /**
     * 薪资待遇
     *
     * @return salary
     **/
    @ApiModelProperty(example = "2", value = "期望薪资:1、3000以下，2、3000-5000，3、5000-10000，4、10000-20000，5、20000以上")
    public Integer getExpectedSalary() {
        return expectedSalary;
    }
    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecruitmentsFullInfo that = (RecruitmentsFullInfo) o;

        if (recruitmentsInfo != null ? !recruitmentsInfo.equals(that.recruitmentsInfo) : that.recruitmentsInfo != null)
            return false;
        if (welfare != null ? !welfare.equals(that.welfare) : that.welfare != null) return false;
        if (expectedSalary != null ? !expectedSalary.equals(that.expectedSalary) : that.expectedSalary != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recruitmentsInfo != null ? recruitmentsInfo.hashCode() : 0;
        result = 31 * result + (welfare != null ? welfare.hashCode() : 0);
        result = 31 * result + (expectedSalary != null ? expectedSalary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecruitmentsFullInfo{" +
                "recruitmentsBase=" + recruitmentsInfo +
                ", welfare='" + welfare + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                '}';
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


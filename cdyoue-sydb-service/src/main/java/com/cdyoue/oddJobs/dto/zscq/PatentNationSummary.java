package com.cdyoue.oddJobs.dto.zscq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * PatentNationSummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

public class PatentNationSummary {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("applicationNO")
    private String applicationNO = null;

    @JsonProperty("publicNO")
    private String publicNO = null;

    @JsonProperty("applicationDate")
    private String applicationDate = null;

    @JsonProperty("inventor")
    private String inventor = null;

    @JsonProperty("patentType")
    private Integer patentType = null;

    @JsonProperty("viewsCount")
    private Integer viewsCount = null;

    @JsonProperty("link")
    private String link = null;

    public PatentNationSummary id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatentNationSummary name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 专利名称
     *
     * @return name
     **/
    @ApiModelProperty(example = "一种聚义", value = "专利名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PatentNationSummary applicationNO(String applicationNO) {
        this.applicationNO = applicationNO;
        return this;
    }

    /**
     * 申请号
     *
     * @return applicationNO
     **/
    @ApiModelProperty(example = "CN1234567890", value = "申请号")
    public String getApplicationNO() {
        return applicationNO;
    }

    public void setApplicationNO(String applicationNO) {
        this.applicationNO = applicationNO;
    }

    public PatentNationSummary publicNO(String publicNO) {
        this.publicNO = publicNO;
        return this;
    }

    /**
     * 公开公告号
     *
     * @return publicNO
     **/
    @ApiModelProperty(example = "CN1234567890", value = "公开公告号")
    public String getPublicNO() {
        return publicNO;
    }

    public void setPublicNO(String publicNO) {
        this.publicNO = publicNO;
    }

    public PatentNationSummary applicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
        return this;
    }

    /**
     * 申请日
     *
     * @return applicationDate
     **/
    @ApiModelProperty(example = "2017/03/03", value = "申请日")
    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public PatentNationSummary inventor(String inventor) {
        this.inventor = inventor;
        return this;
    }

    /**
     * 发明人
     *
     * @return inventor
     **/
    @ApiModelProperty(example = "李某某", value = "发明人")
    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public PatentNationSummary patentType(Integer patentType) {
        this.patentType = patentType;
        return this;
    }

    /**
     * 专利类别（专利特有）：1 发明专利，2 实用新型，3 外观设计专利等
     *
     * @return patentType
     **/
    @ApiModelProperty(example = "2", value = "专利类别（专利特有）：1 发明专利，2 实用新型专利，3 外观设计专利等")
    public Integer getPatentType() {
        return patentType;
    }

    public void setPatentType(Integer patentType) {
        this.patentType = patentType;
    }

    public PatentNationSummary viewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
        return this;
    }

    /**
     * 浏览量（admin）
     *
     * @return viewsCount
     **/
    @ApiModelProperty(example = "10000", value = "浏览量（admin）")
    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatentNationSummary patentNationSummary = (PatentNationSummary) o;
        return Objects.equals(this.id, patentNationSummary.id) &&
                Objects.equals(this.name, patentNationSummary.name) &&
                Objects.equals(this.applicationNO, patentNationSummary.applicationNO) &&
                Objects.equals(this.publicNO, patentNationSummary.publicNO) &&
                Objects.equals(this.applicationDate, patentNationSummary.applicationDate) &&
                Objects.equals(this.inventor, patentNationSummary.inventor) &&
                Objects.equals(this.patentType, patentNationSummary.patentType) &&
                Objects.equals(this.viewsCount, patentNationSummary.viewsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, applicationNO, publicNO, applicationDate, inventor, patentType, viewsCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PatentNationSummary {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    applicationNO: ").append(toIndentedString(applicationNO)).append("\n");
        sb.append("    publicNO: ").append(toIndentedString(publicNO)).append("\n");
        sb.append("    applicationDate: ").append(toIndentedString(applicationDate)).append("\n");
        sb.append("    inventor: ").append(toIndentedString(inventor)).append("\n");
        sb.append("    patentType: ").append(toIndentedString(patentType)).append("\n");
        sb.append("    viewsCount: ").append(toIndentedString(viewsCount)).append("\n");
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

    @ApiModelProperty(example = "/H5/nationalpatent.html?id=1234", value = "H5链接")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}


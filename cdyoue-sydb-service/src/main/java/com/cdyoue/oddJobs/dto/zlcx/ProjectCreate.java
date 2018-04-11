package com.cdyoue.oddJobs.dto.zlcx;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by sky on 2017/9/27.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:24:34.814Z")
public class ProjectCreate {
    @JsonProperty("content")
    private String content = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("projectNumber")
    private String projectNumber= null;

    @JsonProperty("source")
    private String source= null;

    @JsonProperty("attachmentUrl")
    private String attachmentUrl= null;

    @JsonProperty("department")
    private String department = null;

    @JsonProperty("startTime")
    private String startTime= null;

    @JsonProperty("endTime")
    private String endTime= null;

    /**
     * 申报内容
     * @return title
     **/
    @ApiModelProperty(example = "项目申报的内容", value = "申报内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 申报名称
     * @return title
     **/
    @ApiModelProperty(example = "项目申报", value = "申报名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 发文号
     * @return startTime
     **/
    @ApiModelProperty(example = "00000000007", value = "发文号")
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }
    /**
     * 信息来源
     * @return endTime
     **/
    @ApiModelProperty(example = "优易大数据", value = "信息来源")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    /**
     * 附件id
     * @return attachmentId
     **/
    @ApiModelProperty(example = "sy/10-9/1a6572d9-6368-47b5-b826-b728db82b64e.jpg", value = "附件id")
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    /**
     * 申报部门
     * @return department
     **/
    @ApiModelProperty(example = "科技部", value = "申报部门")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    /**
     * 申报开始时间
     * @return department
     **/
    @ApiModelProperty(example = "2017-09-09", value = "申报开始时间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**
     * 申报结束时间
     * @return department
     **/
    @ApiModelProperty(example = "2017-10-08", value = "申报结束时间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

package com.cdyoue.oddJobs.dto.zscq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/5/18.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T00:49:03.408Z")
public class IntellectualMIni {
    @JsonProperty("reviewReason")
    private String reviewReason;

    /**
     * 审核理由
     * @return
     */
    @ApiModelProperty(example = "这是审核的理由", value = "审核理由")
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }
}

package com.cdyoue.oddJobs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luanyu on 2017/6/1.
 */
public class Reason {

    @JsonProperty("reason")
    private String reason = null;

    /**
     * review reason
     * @return  reason
     **/
    @ApiModelProperty(example = "通过", value = "reason")

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

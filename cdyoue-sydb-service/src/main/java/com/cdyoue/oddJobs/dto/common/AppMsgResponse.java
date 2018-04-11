package com.cdyoue.oddJobs.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tr on 2017/6/8.
 */
public class AppMsgResponse {
    @JsonProperty("unreadCount")
    private Integer unreadCount;

    @JsonProperty("msgType")
    private Integer msgType;

    @ApiModelProperty(example = "10", value = "未读消息数")
    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    @ApiModelProperty(example = "1", value = "消息分类（类别）")
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
}

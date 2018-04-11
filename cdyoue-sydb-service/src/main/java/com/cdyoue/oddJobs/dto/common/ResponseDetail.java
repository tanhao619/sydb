package com.cdyoue.oddJobs.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;



/**
 * Created by tr on 2017/6/8.
 */
public class ResponseDetail {
    @JsonProperty("id")
    private int id;

    @JsonProperty("info")
    private String info;

    @JsonProperty("msgType")
    private Integer msgType;

    @JsonProperty("createTime")
    private String createTime;

    @JsonProperty("eventType")
    private Integer eventType;

    @JsonProperty("eventId")
    private Integer eventId;

    @JsonProperty("h5link")
    private String h5link;

    @JsonProperty("type")
    private Integer type;


    @JsonProperty("lookStatus")
    private Boolean lookStatus;

    @JsonProperty("quesReplyId")
    private Integer quesReplyId;

    @ApiModelProperty(value = "消息类型（该类型用于将消息改为已读状态使用 ）")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ApiModelProperty(value = "消息id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty(value = "标题")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @ApiModelProperty(value = "msgType类别")
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @ApiModelProperty(value = "消息创建标准时间")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @ApiModelProperty(value = "eventType业务类型")
    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    @ApiModelProperty(value = "具体业务id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @ApiModelProperty(value = "H5链接")
    public String getH5link() {
        return h5link;
    }

    public void setH5link(String h5link) {
        this.h5link = h5link;
    }


    @ApiModelProperty(value = "消息是否查看 0/false 未查看 1/true 已查看")
    public Boolean getLookStatus() {
        return lookStatus;
    }

    @ApiModelProperty(value = "设置问题replyId,用于改变查看状态,用户关注的问题有回答，即type=3的时候更新lookStatus状态")
    public Integer getQuesReplyId() {
        return quesReplyId;
    }

    public void setQuesReplyId(Integer quesReplyId) {
        this.quesReplyId = quesReplyId;
    }

    public void setLookStatus(Boolean lookStatus) {
        this.lookStatus = lookStatus;
    }
}

package com.cdyoue.oddJobs.dto.message;

import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/6.
 */
public class PersonalCenterMessageDto{

    @JsonProperty("messageDetail")
    private String messageDetail;

    @JsonProperty("eventId")
    private int eventId;
    private int eventTypeEnum;
    private int msgTypeEnum;
    private Boolean lookStatus;
    private int messageId;
    // type为-1为Message表  1代表MessageRel的能人发布的文章 2代表MessageRel的提出的问题
    private Integer type;
    private Date messageTime;
    // 额外信息，什么都可以放存，放新增需求字段
    private String extra;
    // 用于处理关注的问题有回答
    private Integer quesReplyId;

    @JsonProperty("portalMessageEntity")
    private PortalMessageEntity portalMessageEntity;

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public PortalMessageEntity getPortalMessageEntity() {
        return portalMessageEntity;
    }

    public void setPortalMessageEntity(final PortalMessageEntity portalMessageEntity) {
        this.portalMessageEntity = portalMessageEntity;
    }

    public int getEventTypeEnum() {
        return eventTypeEnum;
    }

    public void setEventTypeEnum(int eventTypeEnum) {
        this.eventTypeEnum = eventTypeEnum;
    }

    public int getMsgTypeEnum() {
        return msgTypeEnum;
    }

    public void setMsgTypeEnum(int msgTypeEnum) {
        this.msgTypeEnum = msgTypeEnum;
    }

    public Boolean getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(Boolean lookStatus) {
        this.lookStatus = lookStatus;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getQuesReplyId() {
        return quesReplyId;
    }

    public void setQuesReplyId(Integer quesReplyId) {
        this.quesReplyId = quesReplyId;
    }

    @Override
    public String toString() {
        return "PersonalCenterMessageDto{" +
                "messageDetail='" + messageDetail + '\'' +
                ", eventId=" + eventId +
                ", eventTypeEnum=" + eventTypeEnum +
                ", msgTypeEnum=" + msgTypeEnum +
                ", lookStatus=" + lookStatus +
                ", messageId=" + messageId +
                ", type=" + type +
                ", messageTime=" + messageTime +
                ", extra='" + extra + '\'' +
                ", quesReplyId=" + quesReplyId +
                ", portalMessageEntity=" + portalMessageEntity +
                '}';
    }
}

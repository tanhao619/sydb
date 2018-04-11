package com.cdyoue.oddJobs.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tangguang on 2017/6/21.
 */
public class MessageBody {
    @JsonProperty("id")
    private int id;

    @JsonProperty("info")
    private String info;

    @JsonProperty("msgType")
    private Integer msgType;

    @JsonProperty("eventType")
    private Integer eventType;

    @JsonProperty("eventId")
    private Integer eventId;

    @JsonProperty("type")
    private Integer type;


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(final Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(final Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(final Integer eventId) {
        this.eventId = eventId;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}

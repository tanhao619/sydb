package com.cdyoue.oddJobs.dto.message;

/**
 * Created by Tangguang on 2017/6/22.
 */
public class RespseMessageOffDto {
    private Integer id;
    private String createBy;
    private int msgType;
    private String createTime;
    private String info;
    private int eventType;
    private Integer eventId;
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy( String createBy) {
        this.createBy = createBy;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType( int msgType) {
        this.msgType = msgType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime( String createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo( String info) {
        this.info = info;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType( int eventType) {
        this.eventType = eventType;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId( Integer eventId) {
        this.eventId = eventId;
    }

    public String getLink() {
        return link;
    }

    public void setLink( String link) {
        this.link = link;
    }
}

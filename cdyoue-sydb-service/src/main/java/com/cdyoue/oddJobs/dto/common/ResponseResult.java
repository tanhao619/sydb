package com.cdyoue.oddJobs.dto.common;

import com.cdyoue.oddJobs.en.StatusEnum;

/**
 * Created by liaoyoule on 2017/5/15.
 */
public class ResponseResult {
    protected int code;
    protected StatusEnum status;
    protected String message;
    protected Object response;
    protected String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

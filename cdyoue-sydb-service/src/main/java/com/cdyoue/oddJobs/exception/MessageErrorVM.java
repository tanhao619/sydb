package com.cdyoue.oddJobs.exception;

import com.cdyoue.oddJobs.dto.common.ResponseResult;
import com.cdyoue.oddJobs.en.StatusEnum;

import java.io.Serializable;

/**
 * Created by liaoyoule on 2017/5/11.
 */
public class MessageErrorVM extends ResponseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String description;



    public MessageErrorVM(Integer code,String message, String description) {
        this.status = StatusEnum.FAIL;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public StatusEnum getStatus() {
        return status;
    }

    @Override
    public int getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MessageErrorVM{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", response=" + response +
                ", description='" + description + '\'' +
                '}';
    }
}

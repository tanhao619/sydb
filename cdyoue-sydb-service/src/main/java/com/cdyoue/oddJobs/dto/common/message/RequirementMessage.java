package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/11.
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequirementMessage implements HttpMessage {
    DELETEFAIL(10,"该需求未关闭无法删除",""),



    BADREQUESTERROR(30,"操作失败","无效的需求id"),
    BADREQUESTERRORFORUSER(32,"操作失败","该用户已受邀,不能重复操作"),
    BADREQUESTERECOMMENDRROR(30,"操作失败","用户未登录推荐系统无法获取推荐"),
    BADREQUESTERECOMMENDNOTFOUNDRROR(40,"操作失败","没有找到合适的推荐");


    private Integer code;
    private String msg;
    private String description;
    private List<RequirementMessage.Error> errors = new ArrayList<>();

    RequirementMessage(Integer code, String msg, String description){
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDescription() {
        return description;
    }

    public List<RequirementMessage.Error> getErrors() {
        return errors;
    }

    public RequirementMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new RequirementMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
                .collect(Collectors.toList());
        return this;
    }


    public static class Error {
        private String filed;
        private String msg;

        public Error() {
        }

        public Error(String filed, String msg) {
            this.filed = filed;
            this.msg = msg;
        }

        public String getFiled() {
            return filed;
        }

        public void setFiled(String filed) {
            this.filed = filed;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

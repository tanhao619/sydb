package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sky on 2017/5/11.
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RecruitmentsMessage implements HttpMessage {

    RECRUITMENTACCEPTFAILED(40, "应聘失败", "只有个人用户才能应聘"),
    RECRUITMENTENTNOT_FOUND(40, "操作失败", "你没有发布任何全职/兼职"),
    RECRUITMENTENTUNAUTHORIZED(60,"操作失败","你没有登录"),
    NO_INVITE_RECRUITMENTENT(40,"操作失败","没有邀请的职位"),

    RECRUITMENTUSERGETSUCCESS(20, "获取个人投递的全职/兼职", "获取个人投递的全职/兼职"),
    RECRUITMENTUSERNOT_FOUND(40, "操作失败", "你没有投递任何全职/兼职"),
    RECRUITMENTUSERUNAUTHORIZED(60,"操作失败","你没有登录"),
    RECRUITMENTENT_FORBIDDENNOT_FOUND(40,"没有内容","没有内容,个人用户没有发布职位，企业用户才能查看自己发布的职位"),

    RECRUITMENTRECOMMENTGEDFAILS(10,"操作失败","智能推荐系统返回空"),
    RECRUITMENTPERMISSIONSFAILS(60,"操作失败","不能给非普通用户推荐职位"),
    RECRUITMENINVATEFAILS(32,"操作失败","不能重复提交邀请"),
    RECRUITMENTRECOMMENTGEDNOT_FOUND(40, "没有内容", "没有智能推荐任何全职/兼职"),
    RECRUITMENTRECOMMENTGEDUNAUTHORIZED(60,"操作失败","你没有登录"),

    RECRUITMENTREDELSUCCESS(20,"删除招聘成功","删除招聘成功"),
    RECRUITMENTREDELFAILD(40,"删除招聘失败","删除招聘不存在");




    private Integer code;
    private String msg;
    private String description;
    private List<RecruitmentsMessage.Error> errors = new ArrayList<>();

    RecruitmentsMessage(Integer code, String msg, String description) {
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

    public List<RecruitmentsMessage.Error> getErrors() {
        return errors;
    }

    public RecruitmentsMessage errors(List<ObjectError> errorList) {
        this.errors = errorList.stream()
                .map(objectError -> new RecruitmentsMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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

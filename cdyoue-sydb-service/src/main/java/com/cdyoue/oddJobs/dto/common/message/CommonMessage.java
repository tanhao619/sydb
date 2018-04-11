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
public enum CommonMessage implements HttpMessage {




    //success

    CREATESUCCESS(20,"创建成功",""),
    DELETESUCCESS(20,"删除成功",""),
    UPDATESUCCESS(20,"修改成功",""),
    SENDSUCCESS(21,"发送成功",""),
    SUCCESS(20,"操作成功",""),



    //error
    CREATEFAIL(10,"创建失败","创建失败"),
    DELETEFAIL(10,"删除失败","删除失败"),
    UPDATEFAIL(10,"更新失败","更新失败"),
    CAPTCHINVALID(10,"操作失败","无效的验证码"),
    CAPTCHTIMEOUT(10,"操作失败","验证码过期"),

    FORBIDDENEDITORiSNOTADMINORCREATOR(30,"操作失败","非管理员或创建者不能操作"),

    ILLEGALTEL(30,"操作失败","不合法的手机号"),
    ILLEGALACCOUNT(30,"操作失败","不合法的账号"),
    TELCAPTCHSENDSUCCESS(20,"手机验证码已发送",""),
    BADTYPE(30,"操作失败" ,"无效的类型" ),
    BADREQUEST(30,"操作失败" ,"请求参数不合法" ),
    TOOMANY(30,"操作失败" ,"一小时内最多发送三条验证码" ),

    GETMYINTELLSALENOTFOUND(40,"获取我出售的知识产权失败","没有数据"),
    GETMYINTELLBUYNOTFOUND(40,"获取我求购的知识产权失败","没有数据"),
    GETSUCCCASENOTFOUND(40,"获取成功案例失败","没有数据"),
    GETMYFOLLOWTALENTNOTFOUND(40,"获取我关注的能人失败","没有数据"),
    GETFINPROJECTNOTFOUND(40,"获取融资项目失败","没有数据"),
    GETINVITEMEREQUIRNOTFOUND(40,"获取我收到的邀请失败","没有数据"),
    GETSUGGESTIONNOTFOUND(40,"获取反馈意见失败","没有数据"),
    GETMYINVITEREQUIRUNAUTH(40,"获取我收到的需求要求失败" , "没有登录"),

    FILEISNOTNULL(30,"上传文件失败","文件为空"), GETSOMEONEREQUIRENOTFOUND(40, "获取某用户承接需求失败", "没有数据");

    private Integer code;
    private String msg;
    private String description;
    private List<CommonMessage.Error> errors = new ArrayList<>();

    CommonMessage(Integer code, String msg, String description){
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

    public List<CommonMessage.Error> getErrors() {
        return errors;
    }

    public CommonMessage errors(List<ObjectError> errorList){
        this.errors = errorList.stream()
                .map(objectError -> new CommonMessage.Error(objectError.getObjectName(), objectError.getDefaultMessage()))
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

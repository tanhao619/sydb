package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthenticationMessage implements HttpMessage {


  /**
   * oauth
   */

  AUTHENTICATIONFAILBYROLE(80,"当前role不具备权限","当前role不具备权限"),



  ACCCOUNTISEXIST(40,"账户已存在","账户已存在已存在"),
  ACCOUNTFORBIDDEN(30,"登录失败","不具备权限"),
  ACCOUNTNOTACTIVATED(41, "查询用户信息失败", "账号未激活"),
  ACCOUNTERRORCLIENT(42, "查询用户信息失败", "无效的CLIENTID"),
  GACCOUNTFORBIDDEN(40, "查询用户信息失败", "账户已被禁止"),


  ACCOUNTEACTIVEFAILD(10, "账号激活失败", "无效的token"),
  ACCOUNTEACTIVESUCCESS(20, "账号激活成功", ""),


  ACCOUNTNOTFOUND(60,"查询用户信息失败","账号不存在"),
  USERUNLOGIN(60, "访问失败", "用户未登录"),
  INVALIDISSUREY(60, "访问失败", "凭证无效的发行者"),
  INVALIDTOKEN(60, "访问失败", "无效的TOKEN"),
  TIMEOUT(61, "访问失败", "TOKEN超时"),
  LoginStatus(20,"访问成功","用户状态：已登录"),
  PasswordTrue(20,"密码正确","密码正确"),
  PasswordFalse(40,"密码错误","密码错误"),
  CREATEACCOUTFALSE(50,"系统异常","生成账户失败"),


  LOGOUTSUCCESS(20, "退出成功", ""),

  FERESHTOKENINVALIDISSUREY(11, "刷新TOKEN失败", "凭证无效的发行者"),
  FERESHTOKENINVALIDTOKEN(12, "刷新TOKEN失败", "无效的TOKEN"),
  FERESHTOKENTIMEOUT(13, "刷新TOKEN失败", "TOKEN超时"),

  ACCOUNTREGISTERNAMEERROR(10, "账号注册失败", "重复的企业名"),
  ACCOUNTREGISTERTYPEERROR(10, "账号注册失败", "无效的TYPE"),
  ACCOUNTREGISTEREMAILERROR(10, "账号注册失败", "电话或邮箱已存在"),
  ACCOUNTREGISTERTIMEOUTRROR(10, "账号激活失败", "链接过期"),
  CodeFalse(10,"注册失败","验证码错误"),
  CodeTimeOut(10,"注册失败","验证码超时"),
  ServerWrong(10,"注册失败","服务器异常"),
  CREATESUCCESS(20,"账号注册成功","账号注册成功");


  private Integer code;
  private String msg;
  private String description;
  private List<Error> errors = new ArrayList<>();

  AuthenticationMessage(Integer code, String msg, String description){
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

  public List<Error> getErrors() {
    return errors;
  }

  public AuthenticationMessage errors(List<ObjectError> errorList){
    this.errors = errorList.stream()
            .map(objectError -> new Error(objectError.getObjectName(), objectError.getDefaultMessage()))
            .collect(Collectors.toList());
    return this;
  }



  public static class Error{
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

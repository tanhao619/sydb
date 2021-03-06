package com.cdyoue.oddJobs.dto.common.message;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IndependentTextMessage implements HttpMessage {


  /**
   * oauth
   */



  INDEPENDENTTEXTPUBLISHCREATESUCCESS(20,"新增成功","新增成功"),
  INDEPENDENTTEXTPUBLISHCREATEFAIL(10,"新增失败","新增失败"),
  INDEPENDENTTEXTPUBLISHDELETESUCCESS(20,"删除成功","删除成功");



  private Integer code;
  private String msg;
  private String description;
  private List<Error> errors = new ArrayList<>();

  IndependentTextMessage(Integer code, String msg, String description){
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

  public IndependentTextMessage errors(List<ObjectError> errorList){
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

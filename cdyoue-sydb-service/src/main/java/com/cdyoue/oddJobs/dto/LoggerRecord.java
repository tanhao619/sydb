package com.cdyoue.oddJobs.dto;

public class LoggerRecord {
    private String userName;
    private String method;
    private String clazz;
    private String ip;
    private String time;
    private String target;
    private String result;
    private String role;
    private String description;

    public String getTarget() {
        return target;
    }


    public void setTarget(String target) {
        this.target = target;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {


        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringBuffer("LoggerRecord{"+
                "userName:" + userName + '\'' +
                ",role:"+role + '\'' +
                ",ip:"+ip + '\'' +
                ",method:" + method + '\'' +
                ",target:" + target + '\'' +
                ",clazz:" + clazz + '\'' +
                ",time:" + time + '\'' +
                ",result:" + result + '\'' +
                ",description:" + description + '\'' +

                '}').toString().replaceAll("'","");
    }
}

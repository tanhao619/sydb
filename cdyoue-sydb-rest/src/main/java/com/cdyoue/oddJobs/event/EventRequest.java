package com.cdyoue.oddJobs.event;

import java.io.Serializable;

/**
 * Created by liaoyoule on 2017/4/26.
 */
public class EventRequest implements Serializable{
    private static final long serialVersionUID = 2432417487199651935L;
    String requestURL;
    String method;
    Integer userId;
    String queryString;
    String args;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public EventRequest(String requestURL, String method, Integer userId, String queryString, String args) {
        this.requestURL = requestURL;
        this.method = method;
        this.userId = userId;
        this.queryString = queryString;
        this.args = args;
    }

    public EventRequest(String requestURL, String method, Integer userId, String queryString) {
		this.requestURL = requestURL;
		this.method = method;
		this.userId = userId;
		this.queryString = queryString;
	}
}

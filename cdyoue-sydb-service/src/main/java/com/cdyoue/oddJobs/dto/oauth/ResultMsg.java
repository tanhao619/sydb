package com.cdyoue.oddJobs.dto.oauth;

public class ResultMsg {

	private Integer code;
	private String error;
	private String error_description;

	public ResultMsg(Integer code,String error, String error_description) {
		this.code = code;
		this.error = error;
		this.error_description = error_description;
	}

	public ResultMsg() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
}

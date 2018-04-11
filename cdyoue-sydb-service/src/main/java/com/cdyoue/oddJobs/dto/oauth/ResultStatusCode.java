package com.cdyoue.oddJobs.dto.oauth;

public enum ResultStatusCode {
	OK(200,"", "OK"),
	SYSTEM_ERR(500,"", "System error"),
	INVALID_CLIENTID(401,"invalid_client_id", "Bad client credential"),
	INVALID_PASSWORD(401,"", "User name or password is incorrect"),
	INVALID_TOKEN(401,"", "Invalid token"),
	INVALID_AUTHENTICATION(401,"", "Invalid login"),
	INVALID_AUTHORITY(403,"", "Invalid authority");

	private Integer code;
	private String error;
	private String error_description;


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

	ResultStatusCode(Integer code, String error, String error_description) {
		this.code = code;
		this.error = error;
		this.error_description = error_description;
	}
}

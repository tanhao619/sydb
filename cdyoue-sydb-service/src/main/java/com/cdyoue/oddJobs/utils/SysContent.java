package com.cdyoue.oddJobs.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysContent {
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
}
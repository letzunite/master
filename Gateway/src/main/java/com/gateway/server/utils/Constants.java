package com.gateway.server.utils;

public interface Constants {
	Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
	String SUCCESS_STATUS_MESAGE = "ok";
	Integer ACTIVE_STATUS = 1;
	Integer INACTIVE_STATUS = 0;
	
	interface URI {
		String GET = "/";
		String PROFILE = "/profile";
		String LOAD_ALL = "/properties/load";
		String GET_ALL = "/properties";
		String CHECK_STATUS = "/status";
		String LOGIN = "/login";
		String REWARD = "/reward";
	}
	
	interface HEADERS {
		String TOKEN_ID = "TOKEN_ID";
		String CLIENT_APP_ID = "CLIENT_APP_ID";
	}
	
	
}

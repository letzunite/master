package com.gateway.server.entity;

import lombok.Data;

@Data
public class UserProfile {

	private Long id;
	private String name;
	private String emailId;
	private String mobileNumber;
	private String password;
	private String additionalInfo;
}

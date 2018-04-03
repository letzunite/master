package com.gateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.server.dto.UserProfileDTO;
import com.gateway.server.entity.RestResponse;
import com.gateway.server.entity.UserProfile;
import com.gateway.server.exception.UtilityException;
import com.gateway.server.service.UserProfileService;
import com.gateway.server.utils.Constants;
import com.gateway.server.utils.RestUtils;

@RestController
@RequestMapping(Constants.URI.PROFILE)
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RestResponse<Boolean>> saveUserProfile(@RequestBody 
			UserProfile userProfile) throws UtilityException {
		return RestUtils.successResponse(userProfileService.save(userProfile));
	}
	
	@RequestMapping
	public ResponseEntity<RestResponse<UserProfileDTO>> getUserProfile(@RequestParam("emailId")
			String emailId) throws UtilityException {
		return RestUtils.successResponse(userProfileService.getByEmail(emailId));
	}
}
package com.gateway.server.service;

import com.gateway.server.dto.UserProfileDTO;
import com.gateway.server.entity.UserProfile;
import com.gateway.server.exception.UtilityException;

public interface UserProfileService {

	Boolean save(UserProfile userProfile) throws UtilityException;

	UserProfileDTO getByEmail(String emailId) throws UtilityException;

}

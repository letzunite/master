package com.gateway.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gateway.server.dto.UserProfileDTO;
import com.gateway.server.entity.UserProfile;
import com.gateway.server.exception.ResponseCode;
import com.gateway.server.exception.UtilityException;
import com.gateway.server.repository.UserProfileRepository;
import com.gateway.server.service.UserProfileService;
import com.gateway.server.utils.CommonUtility;
import com.gateway.server.utils.Constants;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public Boolean save(UserProfile userProfile) throws UtilityException {
		UserProfileDTO profileDTO = getUserProfileDTO(userProfile);
		userProfileRepository.save(profileDTO);
		return Boolean.TRUE;
	}
	
	@Override
	public UserProfileDTO getByEmail(String emailId) throws UtilityException {
		List<UserProfileDTO> userProfileList = userProfileRepository.findByEmailId(emailId);
		if (! CommonUtility.isValidList(userProfileList))
			throw new UtilityException(ResponseCode.USER_DATA_NOT_FOUND_IN_RESPONSE);
		return userProfileList.get(0);
	}
 	
	private UserProfileDTO getUserProfileDTO(UserProfile userProfile) {
		if (! (CommonUtility.isValidString(userProfile.getName()) && CommonUtility.isValidString(userProfile.getEmailId())
				&& CommonUtility.isValidString(userProfile.getPassword())))
			throw new UtilityException(ResponseCode.USER_DATA_NOT_FOUND_IN_REQUEST);
		UserProfileDTO profileDTO = new UserProfileDTO();
		profileDTO.setName(userProfile.getName());
		profileDTO.setMobileNumber(userProfile.getMobileNumber());
		profileDTO.setPassword(userProfile.getPassword());
		profileDTO.setEmailId(userProfile.getEmailId());
		profileDTO.setStatus(Constants.ACTIVE_STATUS);
		profileDTO.setAdditionalInfo(userProfile.getAdditionalInfo());
		return profileDTO;
	}
}

package com.gateway.server.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gateway.server.dto.TokenDTO;
import com.gateway.server.dto.UserProfileDTO;
import com.gateway.server.entity.LoginRequestData;
import com.gateway.server.entity.LoginResponseData;
import com.gateway.server.exception.ResponseCode;
import com.gateway.server.exception.UtilityException;
import com.gateway.server.repository.TokenRepository;
import com.gateway.server.repository.UserProfileRepository;
import com.gateway.server.service.LoginService;
import com.gateway.server.utils.CommonUtility;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public LoginResponseData login(LoginRequestData loginRequestdata) throws UtilityException {
		validate(loginRequestdata);
		UserProfileDTO profileDTO = getUserProfileData(loginRequestdata);
		if (! profileDTO.getPassword().equals(loginRequestdata.getPassword()))
				throw new UtilityException(ResponseCode.INVALID_PASSWORD);
		tokenRepository.deleteByUserId(profileDTO.get_id());
		String tokenId = UUID.randomUUID().toString();
		saveToken(loginRequestdata.getDeviceId(), profileDTO.get_id(), tokenId);
		return getLoginResponse(profileDTO, tokenId);
	}

	private LoginResponseData getLoginResponse(UserProfileDTO profileDTO, String tokenId) {
		LoginResponseData responseData = new LoginResponseData();
		responseData.setTokenId(tokenId);
		responseData.setUserProfile(profileDTO);
		return responseData;
	}

	private void saveToken(String deviceId, String userId, String tokenId) {
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setDeviceId(deviceId);
		tokenDTO.setExpiryTime(new Timestamp(Instant.now().toEpochMilli()));
		tokenDTO.setIsDeleted(0);
		tokenDTO.setTokenId(tokenId);
		tokenDTO.setUserId(userId);
		tokenRepository.save(tokenDTO);
	}

	private UserProfileDTO getUserProfileData(LoginRequestData loginRequestdata) throws UtilityException {
		List<UserProfileDTO> userProfileList = userProfileRepository.findByEmailId(loginRequestdata.getEmailId());
		if (! CommonUtility.isValidList(userProfileList))
			throw new UtilityException(ResponseCode.USER_NEEDS_SIGNUP);
		return userProfileList.get(0);
	}

	private void validate(LoginRequestData loginRequestdata) throws UtilityException {
		if (! (CommonUtility.isValidString(loginRequestdata.getEmailId()) && CommonUtility.isValidString(loginRequestdata.getDeviceId())
				&& CommonUtility.isValidString(loginRequestdata.getPassword())))
			throw new UtilityException(ResponseCode.USER_DATA_NOT_FOUND_IN_REQUEST);
	}
}

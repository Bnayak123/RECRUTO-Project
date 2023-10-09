package com.tyssSpark.recrutouserservice.service;

import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuth;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import org.springframework.stereotype.Service;




@Service
public interface UserAuthService {

	public UserAuthResponse login(UserAuth userAuth);

	public UserAuthResponse changePassword(UserAuth userAuth);

	public Boolean addUser(User user);

}

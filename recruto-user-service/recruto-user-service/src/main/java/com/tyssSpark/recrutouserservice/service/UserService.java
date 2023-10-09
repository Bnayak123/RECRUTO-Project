package com.tyssSpark.recrutouserservice.service;


import com.tyssSpark.recrutouserservice.dto.*;

public interface UserService {

	public UserRole getUserRole(UserAuth userAuth);

	public UserAuthResponse addUser(UserRequest request);

	public UserAuthResponse deleteUser(User user);

	public UserAuthResponse editUser(UserRequest request);
	
	public User objectForUser(UserAuth userAuth );

}

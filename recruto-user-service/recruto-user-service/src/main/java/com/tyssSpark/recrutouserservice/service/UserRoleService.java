package com.tyssSpark.recrutouserservice.service;


import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.dto.UserRole;

public interface UserRoleService {

	public UserRole getUserRole(User userDetails);

	public UserAuthResponse addRole(UserRole role);

	public UserRole getUserRoleId(UserRole role);

	public UserAuthResponse deleteRole(UserRole role);

	public UserAuthResponse editRole(UserRole role);

}

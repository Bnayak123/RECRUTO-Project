package com.tyssSpark.recrutouserservice;

import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuth;
import com.tyssSpark.recrutouserservice.dto.UserRole;
import com.tyssSpark.recrutouserservice.repo.UserRepo;
import com.tyssSpark.recrutouserservice.service.UserRoleService;
import com.tyssSpark.recrutouserservice.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecrutoUserServiceApplicationTests {

	@InjectMocks
	UserServiceImpl userServiceimpl;

	@Mock
	private UserRoleService roleService;

	@Mock
	private UserRepo userRepo;


	@Test
	public void getUserRoleTest(){
		UserRole role = new UserRole();
		when(roleService.getUserRole(any())).thenReturn(role);

		User user = new User();
		user.setIsActive(true);
		when(userRepo.findByUserId(any())).thenReturn(user);

		UserAuth userAuth = new UserAuth();
		userAuth.setUserId("gsh");
		userServiceimpl.getUserRole(userAuth);
	}



}

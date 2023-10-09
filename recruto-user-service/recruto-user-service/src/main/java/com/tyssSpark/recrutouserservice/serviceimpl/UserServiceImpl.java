package com.tyssSpark.recrutouserservice.serviceimpl;

import com.tyssSpark.recrutouserservice.dto.*;
import com.tyssSpark.recrutouserservice.repo.UserRepo;
import com.tyssSpark.recrutouserservice.service.UserAuthService;
import com.tyssSpark.recrutouserservice.service.UserRoleService;
import com.tyssSpark.recrutouserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRoleService roleService;

	@Autowired
	private UserAuthService authService;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserRole getUserRole(UserAuth userAuth) {
		String methodName = "getUserRole() ";
		User userDetails = null;
		UserRole role = null;
		try {
			if (userAuth != null && userAuth.getUserId() != null) {

				userDetails = userRepo.findByUserId(userAuth.getUserId());
				objectForUser(userAuth);
				
				if (userDetails != null && userDetails.getIsActive() != null && userDetails.getIsActive() == true) {

					role = roleService.getUserRole(userDetails);

				} else {
					log.info(methodName + " User is Inactive ");
				}
			}
		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return role;
	}

	@Override
	public UserAuthResponse addUser(UserRequest request) {
		String methodName = "addUser()";
		try {
			if (request != null && request.getUser() != null && request.getRole() != null
					&& request.getUser().getUserId() != null && !request.getUser().getUserId().isEmpty()
					&& request.getUser().getUserName()!=null && !request.getUser().getUserName().isEmpty()
					&& request.getUser().getUserEmail() != null && !request.getUser().getUserEmail().isEmpty()
					&& request.getRole().getUserRoleName() != null && !request.getRole().getUserRoleName().isEmpty()) {

				User user = request.getUser();
				UserRole role = request.getRole();

				role = roleService.getUserRoleId(role);
				if (role != null && role.getRoleId() != null) {

					User existingUser = userRepo.findByUserId(user.getUserId());
					if (existingUser != null) {
						if(existingUser.getIsActive()==true) {
						log.info(methodName + " User Already Exist");
						return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
						} else {
							existingUser.setIsActive(true);
							existingUser.setUserEmail(user.getUserEmail());
							existingUser.setUserRoleId(role.getRoleId());
							
							userRepo.save(existingUser);
							
							return UserAuthResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success").build();
						}
					}
					user.setUserRoleId(role.getRoleId());
					user.setIsActive(true);

					Boolean result = authService.addUser(user);
					if (result) {
						userRepo.save(user);

						log.info(methodName + " Data Added Successfully against employeId: {}", user.getUserId());
						return UserAuthResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success").build();
					
					} else {
						log.info(methodName + " Data Could not be Added against employeId: {}", user.getUserId());

					}

				} else {
					log.info(methodName + " Invalid Role : {}", request.getRole().getUserRoleName());
				}

			} else {
				log.info(methodName + " Incomplete User details ");
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during Adding user : {} ", e);
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail").build();

	}

	@Override
	public UserAuthResponse deleteUser(User user) {
		String methodName = "deleteUser()";
		try {
			if (user != null && user.getUserId() != null && !user.getUserId().isEmpty()) {

				User userDetails = userRepo.findByUserId(user.getUserId());

				if (userDetails != null && userDetails.getIsActive() != null && userDetails.getIsActive() == true) {
					userDetails.setIsActive(false);

					userRepo.save(userDetails);

					return UserAuthResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
							.build();
				} else {
					log.info(methodName + " Invalid User / User Not present in db {} ", user.getUserId());
				}

			} else {
				log.info(methodName + " Request is null / empty {} ", user);
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail").build();
	}

	@Override
	public UserAuthResponse editUser(UserRequest request) {
		String methodName = "editUser()";
		boolean update = false;
		try {
			if (request != null && request.getUser() != null && request.getRole() != null) {

				User user = request.getUser();
				UserRole role = request.getRole();

				if (user != null && user.getUserId() != null && !user.getUserId().isEmpty()
						&& ((user.getUserEmail() != null && !user.getUserEmail().isEmpty()) || (role != null
								&& role.getUserRoleName() != null && role.getUserRoleName().isEmpty()))) {

					User userDetails = userRepo.findByUserId(user.getUserId());

					if (userDetails != null) {
						if (user.getUserEmail() != null && !user.getUserEmail().isEmpty()
								&& !userDetails.getUserEmail().equalsIgnoreCase(user.getUserEmail())) {
							update = true;
							userDetails.setUserEmail(user.getUserEmail());
						} else {

							log.info(methodName + " No data to be updated in UserDetails");
						}
					} else {
						log.info(methodName + " No User Found Against the UserId: {} ", user.getUserId());
					}

					if (role != null && role.getUserRoleName() != null && !role.getUserRoleName().isEmpty()) {

						UserRole userRole = roleService.getUserRoleId(role);
						if (userRole != null && userRole.getRoleId() != null
								&& !(userRole.getRoleId() == user.getUserRoleId())) {
							update = true;
							userDetails.setUserRoleId(role.getRoleId());
						} else {
							log.info(methodName + " No role Found Against Role name : {} ", role.getUserRoleName());
						}
					} else {
						log.info(methodName + " No data is been Update for role");
					}

					if (update) {
						userRepo.save(userDetails);

						log.info(methodName + " UserDetails Updated ");
					} else {
						log.info(methodName + " No data Updated to save");
					}

				} else {
					log.info(methodName + " No records to be Updated  : {}", request );
				}

			} else {

				log.info(methodName + " request object is null / empty : {} ", request);
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail").build();
	}

	@Override
	public User objectForUser(UserAuth userAuth) {
		
		return userRepo.findByUserId(userAuth.getUserId());
	}

}

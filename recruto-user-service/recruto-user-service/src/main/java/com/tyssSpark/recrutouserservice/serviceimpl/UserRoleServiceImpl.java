package com.tyssSpark.recrutouserservice.serviceimpl;

import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.dto.UserRole;
import com.tyssSpark.recrutouserservice.repo.UserRoleRepo;
import com.tyssSpark.recrutouserservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepo roleRepo;

	@Override
	public UserRole getUserRole(User userDetails) {
		String methodName = "getUserRole()";
		UserRole userRole = null;
		try {
			if (userDetails != null && userDetails.getUserRoleId() != null) {

				userRole = roleRepo.findByRoleId(userDetails.getUserRoleId());

			} else {
				log.info(methodName + " Request is null / role id is null ");
			}
		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return userRole;
	}

	@Override
	public UserAuthResponse addRole(UserRole role) {
		String methodName = "addRole()";
		try {
			if (role != null && role.getUserRoleName() != null && !role.getUserRoleName().isEmpty()) {

				UserRole existingUserRole = roleRepo.findByUserRoleName(role.getUserRoleName());

				if (existingUserRole != null ) {

					log.info(methodName + " Role already Present : {}", role.getUserRoleName());

					return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST + "").statusDesc("Failed")
							.build();
					}
				UserRole userRole = roleRepo.save(role);

				if (userRole != null) {

					log.info(methodName + " Role Successfully added to db");

					return UserAuthResponse.builder().statusCode(HttpStatus.OK .value()+ "").statusDesc("Success").build();
				} else {

					log.info(methodName + " Role Cound not be saved to db");
				}
			} else {
				log.info(methodName + " request is null / rolename is null ");
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST + "").statusDesc("Failed").build();
	}

	@Override
	public UserRole getUserRoleId(UserRole role) {
		String methodName = "getUserRoleId()";
		UserRole userRole = null;
		try {
			if (role != null && role.getUserRoleName() != null && !role.getUserRoleName().isEmpty()) {

				userRole = roleRepo.findByUserRoleName(role.getUserRoleName());
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return userRole;
	}

	@Override
	public UserAuthResponse deleteRole(UserRole role) {
		
		String methodName = "deleteRole";
		try {
			
			if(role!=null && role.getUserRoleName()!=null && !role.getUserRoleName().isEmpty()) {
				
				UserRole userRole = roleRepo.findByUserRoleName(role.getUserRoleName());
				if(userRole!=null) {
					roleRepo.delete(userRole);
					log.info(methodName + " Role Deleted sucessfully");
					return UserAuthResponse.builder()
							.statusCode(HttpStatus.OK.value() + "")
							.statusDesc("Success")
							.build();

				} else {
					log.info(methodName + " Invalid Role : {} ",role.getUserRoleName() );
				}
				
			} else {
				log.info(methodName + " Request Object is null or empty");
			}
			
		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}

		return UserAuthResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST .value()+ "")
				.statusDesc("Fail").build();
	}

	@Override
	public UserAuthResponse editRole(UserRole role) {
		String methodName = "editRole()";
		try {
			if (role != null && role.getUserRoleName() != null && !role.getUserRoleName().isEmpty()
					&& role.getUserRoleDesc() != null && !role.getUserRoleDesc().isEmpty()) {

				UserRole userRole = roleRepo.findByUserRoleName(role.getUserRoleName());

				if (userRole != null && userRole.getRoleId() != null) {

					userRole.setUserRoleDesc(role.getUserRoleDesc());

					roleRepo.save(userRole);

					log.info(methodName + " Role details Updated Against role Name: {} ", role.getUserRoleName());
					
					return UserAuthResponse.builder()
							.statusCode(HttpStatus.OK.value()+"")
							.statusDesc("Success").build();
				} else {

					log.info(methodName + " no role Present in db");
				}
			} else {
				log.info(methodName + " request is empty {} ", role);
			}
		} catch (Exception e) {
			log.error(methodName + " Exception occurred during loging in : {} ", e);
		}
		return UserAuthResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value()+"")
				.statusDesc("Success").build();
	}

}

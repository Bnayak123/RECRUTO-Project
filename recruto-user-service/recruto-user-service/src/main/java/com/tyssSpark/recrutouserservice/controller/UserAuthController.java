package com.tyssSpark.recrutouserservice.controller;

import com.tyssSpark.recrutouserservice.dto.UserAuth;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;



@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/service/v1.0/user/auth")
public class UserAuthController {

	@Autowired
	private UserAuthService authService;

	/*
	 * login() is used to authenticate user and allow him to login here will be
	 * taken request from UI and invoke Service layer to execute business logic
	 */
	@PostMapping("/login")
	public UserAuthResponse login(@RequestBody UserAuth userAuth) {
		UserAuthResponse authResponse = authService.login(userAuth);
		log.info("Status Code : {} ", authResponse.getStatusCode());
		return authResponse;
	}

	/*
	 * changePassword() is used to validate the user entered password and change
	 * existing user password in db by invoking service layer to execute business
	 * login
	 */
	@PostMapping("/changePassword")
	public UserAuthResponse changePassword(@RequestBody UserAuth userAuth) {
		return authService.changePassword(userAuth);
	}
	

}

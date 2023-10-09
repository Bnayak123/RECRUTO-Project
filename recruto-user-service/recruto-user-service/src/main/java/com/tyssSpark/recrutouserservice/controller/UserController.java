package com.tyssSpark.recrutouserservice.controller;

import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.dto.UserRequest;
import com.tyssSpark.recrutouserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@RestController
@CrossOrigin
@RequestMapping("/service/v1.0/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public UserAuthResponse addUser(@RequestBody UserRequest request) {
		return userService.addUser(request);
	}
	
	@PostMapping("/delete")
	public UserAuthResponse deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}
	
	public UserAuthResponse editUser(@RequestBody UserRequest request) {
		return userService.editUser(request);
	}

}

package com.tyssSpark.recrutouserservice.controller;

import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.dto.UserRole;
import com.tyssSpark.recrutouserservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
//@Slf4j
@RequestMapping("/service/v1.0/user/role")
public class UserRoleController {
	
	@Autowired
	private UserRoleService roleService;
	
	@PostMapping("/add")
	public UserAuthResponse addRole(@RequestBody UserRole role) {
		return roleService.addRole(role);
	}
	
	@PostMapping("/delete")
	public UserAuthResponse deleteRole(@RequestBody UserRole role) {
		return roleService.deleteRole(role);
	}
	
	public UserAuthResponse editRole(@RequestBody UserRole role) {
		return roleService.editRole(role);
	}

}

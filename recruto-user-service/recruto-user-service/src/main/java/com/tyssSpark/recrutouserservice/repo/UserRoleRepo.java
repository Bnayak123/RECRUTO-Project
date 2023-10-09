package com.tyssSpark.recrutouserservice.repo;

import com.tyssSpark.recrutouserservice.dto.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{
	
//	public UserRole findById(Integer Id);
	
	public UserRole  findByRoleId(Integer id);
	
	public UserRole  findByUserRoleName(String userRoleName);
}

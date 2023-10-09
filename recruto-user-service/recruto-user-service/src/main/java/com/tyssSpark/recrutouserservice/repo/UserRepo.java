package com.tyssSpark.recrutouserservice.repo;

import com.tyssSpark.recrutouserservice.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;





public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByUserId(String userId);

}

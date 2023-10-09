package com.tyssSpark.recrutouserservice.repo;

import com.tyssSpark.recrutouserservice.dto.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Integer> {
	/*
	 * findByUserId() is a method used to fetch user details from database based on userId
	 */
	public UserAuth findByUserId(String userId);

}

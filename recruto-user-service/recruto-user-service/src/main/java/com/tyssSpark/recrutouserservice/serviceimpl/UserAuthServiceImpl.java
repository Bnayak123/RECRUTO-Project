package com.tyssSpark.recrutouserservice.serviceimpl;

import com.tyssSpark.recrutouserservice.config.JasyptEncryptionConfig;
import com.tyssSpark.recrutouserservice.constant.UserConstant;
import com.tyssSpark.recrutouserservice.dto.User;
import com.tyssSpark.recrutouserservice.dto.UserAuth;
import com.tyssSpark.recrutouserservice.dto.UserAuthResponse;
import com.tyssSpark.recrutouserservice.dto.UserRole;
import com.tyssSpark.recrutouserservice.repo.UserAuthRepo;
import com.tyssSpark.recrutouserservice.service.UserAuthService;
import com.tyssSpark.recrutouserservice.service.UserService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;



@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {
	
	private String ClassName =UserAuthServiceImpl.class.getSimpleName();

	@Autowired
	private UserAuthRepo authRepo;

	@Autowired
	private JasyptEncryptionConfig encryptConfig;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${publish.test}")
	private String url;


	/**
	 * login() is used to Fetch the user records from database using userId and
	 * compare the user entered password with password that is stored in database
	 * and returns the status accordingly
	 * 
	 * @request UserAuth object
	 * 
	 * @return UserAuthResponse object
	 */
	@Override
	public UserAuthResponse login(UserAuth userAuth) {
		String methodName = ClassName + " " + new UserAuthServiceImpl() {}.getClass().getEnclosingMethod().getName();
		log.info(methodName + " Method has bean invoked to validate user ");
		if (userAuth != null && userAuth.getUserId() != null && !userAuth.getUserId().isEmpty()
				&& userAuth.getPassword() != null && !userAuth.getPassword().isEmpty()) {
			UserAuth userDetails = null;
			try {
				userDetails = authRepo.findByUserId(userAuth.getUserId());
				if (userDetails != null && userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
					String Password = decryptPassword(userDetails.getPassword());

					if (Password.equals(userAuth.getPassword())) {

						log.info(methodName + " Password match Agaist the Employee Id : {} ", userAuth.getUserId());

						UserRole role = userService.getUserRole(userAuth);
						User objectForUser = userService.objectForUser(userAuth);

						if (role != null) {
							log.info(methodName + " role Found Against Employee Id : {} , role : {} ",
									userAuth.getUserId(), role.getUserRoleName());

//							HttpHeaders headers = new HttpHeaders();
//							headers.setContentType(MediaType.APPLICATION_JSON);
//							HttpEntity<UserAuth> entity = new HttpEntity<UserAuth>(userDetails,headers);
//							log.info(entity.toString());
							//ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class );
//							ResponseEntity<String> exchange = restTemplate.postForEntity(url, userAuth, String.class);
							return UserAuthResponse.builder().statusCode(HttpStatus.OK.value()+ "")
									.statusDesc("Success").unit(role.getUserRoleName()).user(objectForUser).build();
						} else {
							log.info(methodName + " No role Found / Role is deleted  for Employee Id : {} ",
									userAuth.getUserId());
							return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
									.statusDesc("No role Assigned").build();
						}
					} else {
						log.info(methodName + " Password didnot match Agaist the Employee Id : {} ",
								userAuth.getUserId());
						return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Invalid Credentials").build();
					}
				} else {

					log.info(methodName + " No record found For employee id : {}", userAuth.getUserId());
					return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
							.statusDesc("Invalid User").build();
				}

			} catch (Exception e) {
				log.error(methodName + " Exception occurred during loging in : {} ", e);
			}
		} else {

			log.info(methodName + " Request Object is empty or null");
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value()+ "").statusDesc("Unexpected error").build();
	}
	/**
	 * method is used to encrypt the user entered password in order to save the
	 * encrypted password in db
	 * 
	 * @param password
	 * 
	 * @return Encrypted password
	 */

	private String encryptPassword(String password) {
		String methodName = ClassName + " " + new UserAuthServiceImpl() {} .getClass().getEnclosingMethod().getName();
		String encryptedPassword = null;
			log.info(methodName + " Method invoked to encryt the password");
			StringEncryptor passwordEncryptor = encryptConfig.getPasswordEncryptor();
			encryptedPassword = passwordEncryptor.encrypt(password);
		return encryptedPassword;
	}

	/**
	 * method is used to decrypt the encrypted password which is recived from
	 * db in orderd to validated the user entered password
	 * 
	 * @param password
	 * 
	 * @return Decrypted password
	 */

	private String decryptPassword(String password) {
		String methodName = ClassName + " "+ new UserAuthServiceImpl() {} .getClass().getEnclosingMethod().getName();;
		String decryptedPassword = null;
			log.info(methodName + " Method invoked to decryt the password");
			StringEncryptor passwordEncryptor = encryptConfig.getPasswordEncryptor();
			decryptedPassword = passwordEncryptor.decrypt(password);
		return decryptedPassword;
	}

	/**
	 * method is used to change the user password by validating the existing
	 * password
	 * 
	 * @request USerAuth object
	 * 
	 * @return UserAuthResponse object
	 */

	// Incomplete
	@Override
	public UserAuthResponse changePassword(UserAuth userAuth) {
		String methodName = ClassName + " " + new UserAuthServiceImpl() {} .getClass().getEnclosingMethod().getName();
		try {
			log.info(methodName + " Method has bean invoked to validate user and change the password ");
			if (userAuth != null && userAuth.getUserId() != null && !userAuth.getUserId().isEmpty()
					&& userAuth.getPassword() != null && !userAuth.getPassword().isEmpty()) {

				UserAuth userDetails = authRepo.findByUserId(userAuth.getUserId());
				if (userDetails != null) {
					String password = encryptPassword(userAuth.getPassword());
					userDetails.setPassword(password);
					UserAuth save = authRepo.save(userDetails);
					if (save != null) {

						return UserAuthResponse.builder().statusCode(HttpStatus.OK.value() + "")
								.statusDesc("Success").build();
					} else {
						log.info(methodName + " data could not be saved");
					}
				} else {
					log.info(methodName + " No record found For employee id : {}", userAuth.getUserId());
				}
			} else {
				log.info(methodName + " User input is empty");
			}

		} catch (Exception e) {
			log.error(methodName + " Exception occurred during Changing password : {} ", e);
		}
		return UserAuthResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail")
				.build();
	}

	/**
	 * 
	 */

	@Override
	public Boolean addUser(User user) {
		String methodName = ClassName + " " + new UserAuthServiceImpl() {} .getClass().getEnclosingMethod().getName();
		boolean result = false;
		if (user != null && user.getUserId() != null && !user.getUserId().isEmpty()) {
			try {
				UserAuth userDetails = new UserAuth();
				userDetails.setUserId(user.getUserId());
				String password = encryptPassword(UserConstant.DEFAULT_PASSWORD);
				userDetails.setPassword(password);
				UserAuth userInfo = authRepo.save(userDetails);
				if (userInfo != null) {
					result = true;
				}
			} catch (Exception e) {
				log.error(methodName + " Exception occurred during Adding user : {} ", e);
			}
		}
		return result;
	}
}

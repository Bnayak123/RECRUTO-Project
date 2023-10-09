package com.tyssSpark.recrutounitservice.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/*
 * class is used to provide encryption and decryption logic in order to
 * provide encrytion logic
 */
@Configuration
public class JasyptEncryptionConfig {

	@Value("${password.private.screctkey.encryption}")
	private String privateKey;

	/*
	 *  method is used to return Encryptor object in ordered perform encryption
	 * on data by added algorithim , privatekey and generator class
	 */
	public StringEncryptor getPasswordEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(privateKey); // encryptor's private key
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

}

package com.tyssSpark.recrutounitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
public class RecrutoUnitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecrutoUnitServiceApplication.class, args);
	}

}

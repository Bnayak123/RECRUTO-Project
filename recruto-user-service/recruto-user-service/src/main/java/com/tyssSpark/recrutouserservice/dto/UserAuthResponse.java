package com.tyssSpark.recrutouserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * Below Class is used to return the response to UI with Status code and Description
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {
	
	private String statusCode;
	private String statusDesc;
	private String unit;
	private String message;
	private User user;
	
	

}

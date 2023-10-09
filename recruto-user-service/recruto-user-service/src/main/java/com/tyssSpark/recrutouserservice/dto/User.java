package com.tyssSpark.recrutouserservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SPARK_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="SPARK_USER_ID", unique = true)
	private String userId;
	@Column(name="SPARK_USER_NAME")
	private String userName;
	@Column(name = "SPARK_USER_EMAIL")
	private String userEmail;
	@Column(name = "SPARK_USER_UNIT_NAME")
	private String userUnitName;
	@Column(name = "SPARK_USER_ROLE_ID")
	private Integer userRoleId;
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	

}

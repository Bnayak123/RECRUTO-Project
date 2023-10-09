package com.tyssSpark.recrutounitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessUnitResponse {
	
    private String edfInitiated;
    private LocalDate deployementDate;
    private String clientName;
    private String businessUnitName;
    private String reportingManagerName;
    private String comments;

    private String traineeName;
    private String traineeEmailId;
    private Long traineeMobileNum;
    private String traineeStatus;
    private Double traineeYearOfExp;


    private String traineeOfficialId;
    private LocalDate traineeOnBoardingDate;
    private String traineeOfficialMailId;
    
    private String benGender;

	public BusinessUnitResponse(String edfInitiated, LocalDate deployementDate, String clientName,
			String businessUnitName, String reportingManagerName, String comments, String traineeName,
			String traineeEmailId, Long traineeMobileNum, String traineeStatus, Double traineeYearOfExp,
			String traineeOfficialId, LocalDate traineeOnBoardingDate, String traineeOfficialMailId) {
		super();
		this.edfInitiated = edfInitiated;
		this.deployementDate = deployementDate;
		this.clientName = clientName;
		this.businessUnitName = businessUnitName;
		this.reportingManagerName = reportingManagerName;
		this.comments = comments;
		this.traineeName = traineeName;
		this.traineeEmailId = traineeEmailId;
		this.traineeMobileNum = traineeMobileNum;
		this.traineeStatus = traineeStatus;
		this.traineeYearOfExp = traineeYearOfExp;
		this.traineeOfficialId = traineeOfficialId;
		this.traineeOnBoardingDate = traineeOnBoardingDate;
		this.traineeOfficialMailId = traineeOfficialMailId;
	}
    
    
}

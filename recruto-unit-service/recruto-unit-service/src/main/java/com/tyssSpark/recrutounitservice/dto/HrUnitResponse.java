package com.tyssSpark.recrutounitservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor

public class HrUnitResponse {

    private LocalDate benMonth;
    private String benSource;
    private String benGender;
    private String benBench;
    private String benBatch;
    private Integer benTraineeDuration;
    private String benSkillSet;
    private String benCtc;
    private String benRevicedCtc;
    private String benRemarks;

    private String traineeOfficialId;
    private LocalDate traineeOnBoardingDate;
    private String hrName;
    private LocalDate traineeDOB;
    private String traineeFatherName;
    private String traineeLocation;
    private String traineeOfficialMailId;
    private String traineePermanentAddress;
    private String traineeJoiningFormalities;
    private String traineeLOIFormalities;
    private String traineeNDAFormalities;
    private String traineeMarksCardDetails;
    private String traineeBioMetricStatus;
    private String traineeICards;
    private String traineeRemarks;

    private String traineeName;
    private String traineeEmailId;
    private Long traineeMobileNum;
    private String traineeStatus;
    private Double traineeYearOfExp;
	public HrUnitResponse(LocalDate benMonth, String benSource, String benGender, String benBench, String benBatch,
			Integer benTraineeDuration, String benSkillSet, String benCtc, String benRevicedCtc, String benRemarks,
			String traineeOfficialId, LocalDate traineeOnBoardingDate, String hrName, LocalDate traineeDOB,
			String traineeFatherName, String traineeLocation, String traineeOfficialMailId,
			String traineePermanentAddress, String traineeJoiningFormalities, String traineeLOIFormalities,
			String traineeNDAFormalities, String traineeMarksCardDetails, String traineeBioMetricStatus,
			String traineeICards, String traineeRemarks, String traineeName, String traineeEmailId,
			Long traineeMobileNum, String traineeStatus, Double traineeYearOfExp) {
		super();
		this.benMonth = benMonth;
		this.benSource = benSource;
		this.benGender = benGender;
		this.benBench = benBench;
		this.benBatch = benBatch;
		this.benTraineeDuration = benTraineeDuration;
		this.benSkillSet = benSkillSet;
		this.benCtc = benCtc;
		this.benRevicedCtc = benRevicedCtc;
		this.benRemarks = benRemarks;
		this.traineeOfficialId = traineeOfficialId;
		this.traineeOnBoardingDate = traineeOnBoardingDate;
		this.hrName = hrName;
		this.traineeDOB = traineeDOB;
		this.traineeFatherName = traineeFatherName;
		this.traineeLocation = traineeLocation;
		this.traineeOfficialMailId = traineeOfficialMailId;
		this.traineePermanentAddress = traineePermanentAddress;
		this.traineeJoiningFormalities = traineeJoiningFormalities;
		this.traineeLOIFormalities = traineeLOIFormalities;
		this.traineeNDAFormalities = traineeNDAFormalities;
		this.traineeMarksCardDetails = traineeMarksCardDetails;
		this.traineeBioMetricStatus = traineeBioMetricStatus;
		this.traineeICards = traineeICards;
		this.traineeRemarks = traineeRemarks;
		this.traineeName = traineeName;
		this.traineeEmailId = traineeEmailId;
		this.traineeMobileNum = traineeMobileNum;
		this.traineeStatus = traineeStatus;
		this.traineeYearOfExp = traineeYearOfExp;
	}
    


}

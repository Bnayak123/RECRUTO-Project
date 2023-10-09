package com.tyssSpark.recrutounitservice.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tyssSpark.recrutounitservice.dto.BenchUnitData;
import com.tyssSpark.recrutounitservice.dto.HrUnitData;
import com.tyssSpark.recrutounitservice.dto.HrUnitResponse;
import com.tyssSpark.recrutounitservice.dto.TraineeData;
import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import com.tyssSpark.recrutounitservice.repo.HrUnitRepo;
import com.tyssSpark.recrutounitservice.repo.TraineeDataRepo;
import com.tyssSpark.recrutounitservice.service.HrUnitService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class HrUnitServiceImpl implements HrUnitService {
	@Autowired
	private HrUnitRepo hrUnitRepo;

	@Autowired
	private TraineeDataRepo traineeDataRepo;

	@Override
	public UnitResponse onBoardTrainee(TraineeDetails traineeDetails) {
		String methodName = new HrUnitServiceImpl() {
		}.getClass().getEnclosingMethod().getName();
		boolean isUpdated = false;
		try {
			if (traineeDetails != null && traineeDetails.getHrUnitData() != null
					&& traineeDetails.getTraineeData() != null) {
				TraineeData traineeData = traineeDetails.getTraineeData();
				HrUnitData hrUnit = traineeDetails.getHrUnitData();
				TraineeData trainee = null;
				if (traineeData.getTraineeMobileNum() != null && traineeData.getTraineeMobileNum() > 0
						&& String.valueOf(traineeData.getTraineeMobileNum()).length() == 10) {
					trainee = traineeDataRepo.findByTraineeMobileNum(traineeData.getTraineeMobileNum());
					if (trainee != null && trainee.getTraineeName() != null && !trainee.getTraineeName().isBlank()
							&& traineeData.getTraineeName() != null && !traineeData.getTraineeName().isEmpty()
							&& !traineeData.getTraineeName().equalsIgnoreCase("Null")
							&& !traineeData.getTraineeName().equalsIgnoreCase("NA")
							&& !trainee.getTraineeName().equals(traineeData.getTraineeName())) {
						trainee.setTraineeName(traineeData.getTraineeName());
						isUpdated = true;
					}
					if (trainee != null && trainee.getTraineeEmailId() != null
							&& traineeData.getTraineeEmailId() != null && !traineeData.getTraineeEmailId().isBlank()
							&& !traineeData.getTraineeEmailId().equalsIgnoreCase("Null")
							&& !traineeData.getTraineeEmailId().equalsIgnoreCase("NA")
							&& !trainee.getTraineeEmailId().equals(traineeData.getTraineeEmailId())) {
						trainee.setTraineeEmailId(traineeData.getTraineeEmailId());
						isUpdated = true;
					}
				}
				if (hrUnit.getTraineeOfficialId() != null && !hrUnit.getTraineeOfficialId().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeOfficialId().equalsIgnoreCase("NA")
						&& !hrUnit.getTraineeOfficialMailId().isBlank()
						&& !hrUnit.getTraineeOfficialMailId().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeOfficialMailId().equalsIgnoreCase("NA") && hrUnit.getHrName() != null
						&& !hrUnit.getHrName().isBlank() && !hrUnit.getHrName().equalsIgnoreCase("Null")
						&& !hrUnit.getHrName().equalsIgnoreCase("NA") && hrUnit.getTraineeFatherName() != null
						&& !hrUnit.getTraineeFatherName().isBlank()
						&& !hrUnit.getTraineeFatherName().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeFatherName().equalsIgnoreCase("NA") && hrUnit.getTraineeLocation() != null
						&& !hrUnit.getTraineeLocation().isEmpty()
						&& !hrUnit.getTraineeLocation().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeLocation().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeOfficialMailId() != null && !hrUnit.getTraineeOfficialMailId().isEmpty()
						&& !hrUnit.getTraineeOfficialMailId().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeOfficialMailId().equalsIgnoreCase("NA")
						&& hrUnit.getTraineePermanentAddress() != null && !hrUnit.getTraineePermanentAddress().isEmpty()
						&& !hrUnit.getTraineePermanentAddress().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineePermanentAddress().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeJoiningFormalities() != null
						&& !hrUnit.getTraineeJoiningFormalities().isEmpty()
						&& !hrUnit.getTraineeJoiningFormalities().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeJoiningFormalities().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeLOIFormalities() != null && !hrUnit.getTraineeLOIFormalities().isEmpty()
						&& !hrUnit.getTraineeLOIFormalities().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeLOIFormalities().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeNDAFormalities() != null && !hrUnit.getTraineeNDAFormalities().isEmpty()
						&& !hrUnit.getTraineeNDAFormalities().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeNDAFormalities().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeMarksCardDetails() != null && !hrUnit.getTraineeMarksCardDetails().isEmpty()
						&& !hrUnit.getTraineeMarksCardDetails().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeMarksCardDetails().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeBioMetricStatus() != null && !hrUnit.getTraineeBioMetricStatus().isEmpty()
						&& !hrUnit.getTraineeBioMetricStatus().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeBioMetricStatus().equalsIgnoreCase("NA")
						&& hrUnit.getTraineeICards() != null && !hrUnit.getTraineeICards().isEmpty()
						&& !hrUnit.getTraineeICards().equalsIgnoreCase("Null")
						&& !hrUnit.getTraineeICards().equalsIgnoreCase("NA") && hrUnit.getCreatedBy() != null
						&& !hrUnit.getCreatedBy().isEmpty() && !hrUnit.getCreatedBy().equalsIgnoreCase("Null")
						&& !hrUnit.getCreatedBy().equalsIgnoreCase("NA") && hrUnit.getTraineeDOB() != null
						&& hrUnit.getTraineeOnBoardingDate() != null && trainee != null) {
					HrUnitData findByTraineeHrUnitOfficialId = hrUnitRepo
							.findByTraineeOfficialId(hrUnit.getTraineeOfficialId());
					if (findByTraineeHrUnitOfficialId == null) {
						hrUnit.setCreatedDate(dateTime());
						hrUnit.setTraineeData(trainee);
//                        hrUnit.setTraineeOnBoardingDate(dateTimeFormat(hrUnit.getTraineeOnBoardingDate()));
//                        hrUnit.setTraineeDOB(dateTimeFormat(hrUnit.getTraineeDOB()));
						HrUnitData savedData = hrUnitRepo.save(hrUnit);
						if (savedData != null && isUpdated == true) {
							traineeDataRepo.save(trainee);
							return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
									.statusDesc("Successfully Saved  the  Data In DataBase and masterDataUpdated")
									.build();
						}
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
								.statusDesc("Successfully Saved  the  Data In DataBase").build();
					} else {
						log.info(methodName + "TranieeData is Already  exist in DataBase");
					}
				} else {
					log.info(methodName + "TranieeData is Missing Enter valid Details");
				}

			} else {
				log.info(methodName + "Data Is Not Suffcient please Enter Valid Data");
			}
		} catch (Exception e) {
			log.error(methodName + " Exception occurred during logging in : {} ", e);
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
				.statusDesc("Failed to saveData in DataBase").build();
	}

	/**
	 * getAllTraineeData method is used to fetch all the data from master table, hr
	 * unit table and bench unit table from the database and order it in benMonth &
	 * in descending order
	 *
	 * @return UnitResponse
	 */
	@Override
	public UnitResponse getAllTraineeData() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName + "entered into the method");
		List<HrUnitResponse> allDataFromAllTable = hrUnitRepo.getAllDataFromAllTable();
		if (allDataFromAllTable != null && !allDataFromAllTable.isEmpty()) {
			Map<String, List<HrUnitResponse>> collect = allDataFromAllTable.stream()
					.collect(Collectors.groupingBy(HrUnitResponse::getBenBench));
			if (collect != null && !collect.isEmpty()) {
				List<List<HrUnitResponse>> valueList = collect.values().stream().collect(Collectors.toList());
				return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
						.hrUnitResponses(valueList).build();
			} else {
				log.info(methodName + "HrDataInfo is null or empty");
			}
		} else {
			log.info(methodName + "HrDataInfo is groupingBy null or empty");
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail").build();
	}

	/**
	 * editDetailsInHRAndMaster() method receives UserRequest object and use it to
	 * edit the Data of HR and Master in DB
	 *
	 * @return UnitResponse
	 */
	
	 @Override
	    public UnitResponse editTraineeDetails(TraineeDetails traineeDetails) {
	        String methodName = HrUnitServiceImpl.class.getSimpleName() + " "
	                + new BenchUnitServiceImpl() {
	        }.getClass().getEnclosingMethod().getName();
	        try {
	            log.info("entered into the " + methodName + "method");
	            if (traineeDetails != null && traineeDetails.getTraineeData() != null
	                    && traineeDetails.getTraineeData().getTraineeMobileNum() > 0 && String.valueOf(traineeDetails.getTraineeData().getTraineeMobileNum()).length()==10) {
	               
	            	
	            	TraineeData traineeData = traineeDetails.getTraineeData();
	            	 if(validateingNaOrNull(traineeData.getTraineeEmailId()) || validateingNaOrNull(traineeData.getTraineeName()) || validateingNaOrNull(traineeData.getUpdatedBy())) {
	            		 return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
                                 .message("trainee has na/null").statusDesc("failed").build(); 
		                }
	            	 
	                TraineeData masterDataInfo = traineeDataRepo.findByTraineeMobileNum(traineeData.getTraineeMobileNum());
	                if (masterDataInfo != null) {
	                    HrUnitData hrUnitInfo = null;
	                    HrUnitData hrUnit=null;
	                    boolean isMasterDataModified = false;
	                    boolean isHRModified = false;
	                    if (traineeDetails.getHrUnitData() != null) {
	                        hrUnit = traineeDetails.getHrUnitData();
	                        hrUnitInfo = new HrUnitData();
	                        if(validateingNaOrNull(hrUnit.getTraineeOfficialId()) || validateingNaOrNull(hrUnit.getHrName()) || validateingNaOrNull(hrUnit.getTraineeFatherName())
	                        		|| validateingNaOrNull(hrUnit.getTraineeLocation()) || validateingNaOrNull(hrUnit.getTraineeOfficialMailId()) || validateingNaOrNull(hrUnit.getTraineePermanentAddress())
	                        		|| validateingNaOrNull(hrUnit.getTraineeJoiningFormalities()) || validateingNaOrNull(hrUnit.getTraineeLOIFormalities()) || validateingNaOrNull(hrUnit.getTraineeNDAFormalities())
	                        		 || validateingNaOrNull(hrUnit.getTraineeMarksCardDetails()) || validateingNaOrNull(hrUnit.getTraineeBioMetricStatus()) || validateingNaOrNull(hrUnit.getTraineeICards())
	                        		 || validateingNaOrNull(hrUnit.getTraineeRemarks()) || validateingNaOrNull(hrUnit.getUpdatedBy())) {
	                        	 return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
	                                     .message("hr has na/null").statusDesc("failed").build(); 
	                        }
	                        if (validateInfo(hrUnit.getTraineeOfficialId())) {
	                            hrUnitInfo.setTraineeOfficialId(hrUnit.getTraineeOfficialId());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getHrName())) {
	                            hrUnitInfo.setHrName(hrUnit.getHrName());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeOnBoardingDate())) {
	                            hrUnitInfo.setTraineeOnBoardingDate(hrUnit.getTraineeOnBoardingDate());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeDOB())) {
	                            hrUnitInfo.setTraineeDOB(hrUnit.getTraineeDOB());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeFatherName())) {
	                            hrUnitInfo.setTraineeFatherName(hrUnit.getTraineeFatherName());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeLocation())) {
	                            hrUnitInfo.setTraineeLocation(hrUnit.getTraineeLocation());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeOfficialMailId())) {
	                            hrUnitInfo.setTraineeOfficialMailId(hrUnit.getTraineeOfficialMailId());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineePermanentAddress())) {
	                            hrUnitInfo.setTraineePermanentAddress(hrUnit.getTraineePermanentAddress());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeJoiningFormalities())) {
	                            hrUnitInfo.setTraineeJoiningFormalities(hrUnit.getTraineeJoiningFormalities());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeLOIFormalities())) {
	                            hrUnitInfo.setTraineeLOIFormalities(hrUnit.getTraineeLOIFormalities());
	                            isHRModified = true;
	                        }

	                        if (validateInfo(hrUnit.getTraineeNDAFormalities())) {
	                            hrUnitInfo.setTraineeNDAFormalities(hrUnit.getTraineeNDAFormalities());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeMarksCardDetails())) {
	                            hrUnitInfo.setTraineeMarksCardDetails(hrUnit.getTraineeMarksCardDetails());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeBioMetricStatus())) {
	                            hrUnitInfo.setTraineeBioMetricStatus(hrUnit.getTraineeBioMetricStatus());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeICards())) {
	                            hrUnitInfo.setTraineeICards(hrUnit.getTraineeICards());
	                            isHRModified = true;
	                        }
	                        if (validateInfo(hrUnit.getTraineeRemarks())) {
	                            hrUnitInfo.setTraineeRemarks(hrUnit.getTraineeRemarks());
	                            isHRModified = true;
	                        }
	                        if(isHRModified) {
	                        	hrUnitInfo.setUpdatedBy(hrUnit.getUpdatedBy());
	                        }

//	                        if (isHRModified) {
//	                            if (validateInfo(hrUnit.getUpdatedBy())) {
//	                                hrUnitInfo.setUpdatedBy(hrUnit.getUpdatedBy());
//	                            } else {
//	                                isHRModified = false;
//	                            }
//	                        }
	                    }
	                        if (validateInfo(traineeData.getTraineeEmailId())) {
	                            masterDataInfo.setTraineeEmailId(traineeData.getTraineeEmailId());
	                            isMasterDataModified = true;
	                        }
	                        if (validateInfo(traineeData.getTraineeName())) {
	                            masterDataInfo.setTraineeName(traineeData.getTraineeName());
	                            isMasterDataModified = true;
	                        }
	                        
	                    
	                    if (isMasterDataModified && isHRModified) {
	                    	if(validateInfo(hrUnitInfo.getUpdatedBy())) {
	                    		if(validateInfo(traineeData.getUpdatedBy())) {
	                        HrUnitData hrDetails = hrUnitRepo.findByTraineeData(masterDataInfo);
	                        boolean isDuplicatedDataEnteredInHR = false;
	                        boolean isDuplicatedDataEnteredInMaster = false;
	                        if (hrDetails != null) {
	                            try {
	                                saveHr(hrDetails, hrUnitInfo);
	                            } catch (DataIntegrityViolationException e) {
	                                isDuplicatedDataEnteredInHR = true;
	                            }
	                            masterDataInfo.setUpdatedDate(dateTime());
	                            masterDataInfo.setUpdatedBy(traineeData.getUpdatedBy());
	                            try {
	                                traineeDataRepo.save(masterDataInfo);
	                            } catch (DataIntegrityViolationException e) {
	                                isDuplicatedDataEnteredInMaster = true;
	                            }
	                            if (isDuplicatedDataEnteredInHR && isDuplicatedDataEnteredInMaster) {
	                                return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details,offical Id/ official EMail ID already exist in HR Unit and also EMail ID/ Mobile Number already exist in Master")
	                                        .statusDesc("failed").build();
	                            }
	                            if (isDuplicatedDataEnteredInHR) {
	                                return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details,offical Id/ official EMail ID already exist in HR Unit")
	                                        .statusDesc("failed").build();
	                            }
	                            if (isDuplicatedDataEnteredInMaster) {
	                                return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details,EMail ID/ Mobile Number already exists"
	                                                + "xist in Master")
	                                        .statusDesc("failed").build();
	                            }
	                            return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
	                                    .message("successfully updated in Master and HR Unit").statusDesc("Success")
	                                    .build();
	                        } else {
	                            return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
	                                    .message("enter no such resource details exist").statusDesc("failed").build();
	                        }
	                    		}else {
	                    			 return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
			                                    .message("master updated by not given").statusDesc("failed").build();
	                    		}
	                    	} else {
	                    		 return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
		                                    .message("hr updated by not given").statusDesc("failed").build();
	                    	}
	                    }
	                    if (isHRModified) { 
	                        HrUnitData hrDetails = hrUnitRepo.findByTraineeData(masterDataInfo);
	                        if (hrDetails != null) {

	                            try {
	                                saveHr(hrDetails, hrUnitInfo);
	                            } catch (DataIntegrityViolationException e) {
	                                return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details, officalId/ official Mail ID already exist")
	                                        .statusDesc("failed").build();
	                            }
	                            return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
	                                    .message("successfully updated in HR Unit").statusDesc("Success").build();
	                        } else {
	                            return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
	                                    .message("enter no such resource details exist").statusDesc("failed").build();
	                        }
	                    }
	                    if (isMasterDataModified) {
	                    	if(validateInfo(traineeData.getUpdatedBy())) {
	                        masterDataInfo.setUpdatedDate(dateTime());
	                        masterDataInfo.setUpdatedBy(traineeData.getUpdatedBy());
	                        try {
	                            traineeDataRepo.save(masterDataInfo);
	                        } catch (DataIntegrityViolationException e) {
	                            return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details,EMail ID/ Mobile Number already exist")
	                                    .statusDesc("failed").build();
	                        }
	                        return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
	                                .message("successfully updated in Master Unit").statusDesc("Success").build();

	                    }else {
	                    	return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
		                            .message("trainee updated by is not given").statusDesc("failed").build();
	                    } }
	                } else {
	                    return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
	                            .message("enter no such resource details exist in master").statusDesc("failed").build();
	                }
	            } else {
	                log.info(methodName + " invalid details entered");
	            }
	        } catch (Exception e) {
	            log.error(methodName + " exception occured in : {} ", e);
	        }
	        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid details")
	                .statusDesc("failed").build();
	    }

	    private boolean validateingNaOrNull(String info) {
	    	if(info.equalsIgnoreCase("na") || info.equalsIgnoreCase("null")) {
	    		return true;
	    	}
		return false;
	}

		/**
	     * saveHr() method is used to save the HR Object in Database
	     */
	    private void saveHr(HrUnitData hrDetails, HrUnitData hrUnitInfo) {
	        try {
	            if (validateInfo(hrUnitInfo.getTraineeOfficialId())) {
	                hrDetails.setTraineeOfficialId(hrUnitInfo.getTraineeOfficialId());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeOnBoardingDate())) {
	                hrDetails.setTraineeOnBoardingDate(hrUnitInfo.getTraineeOnBoardingDate());
	            }

	            if (validateInfo(hrUnitInfo.getHrName())) {
	                hrDetails.setHrName(hrUnitInfo.getHrName());
	            }

	            if (validateInfo(hrUnitInfo.getTraineeDOB())) {
	                hrDetails.setTraineeDOB(hrUnitInfo.getTraineeDOB());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeFatherName())) {
	                hrDetails.setTraineeFatherName(hrUnitInfo.getTraineeFatherName());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeLocation())) {
	                hrDetails.setTraineeLocation(hrUnitInfo.getTraineeLocation());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeOfficialMailId())) {
	                hrDetails.setTraineeOfficialMailId(hrUnitInfo.getTraineeOfficialMailId());
	            }
	            if (validateInfo(hrUnitInfo.getTraineePermanentAddress())) {
	                hrDetails.setTraineePermanentAddress(hrUnitInfo.getTraineePermanentAddress());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeJoiningFormalities())) {
	                hrDetails.setTraineeJoiningFormalities(hrUnitInfo.getTraineeJoiningFormalities());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeLOIFormalities())) {
	                hrDetails.setTraineeLOIFormalities(hrUnitInfo.getTraineeLOIFormalities());
	            }

	            if (validateInfo(hrUnitInfo.getTraineeNDAFormalities())) {
	                hrDetails.setTraineeNDAFormalities(hrUnitInfo.getTraineeNDAFormalities());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeMarksCardDetails())) {
	                hrDetails.setTraineeMarksCardDetails(hrUnitInfo.getTraineeMarksCardDetails());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeBioMetricStatus())) {
	                hrDetails.setTraineeBioMetricStatus(hrUnitInfo.getTraineeBioMetricStatus());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeICards())) {
	                hrDetails.setTraineeICards(hrUnitInfo.getTraineeICards());
	            }
	            if (validateInfo(hrUnitInfo.getTraineeRemarks())) {
	                hrDetails.setTraineeRemarks(hrUnitInfo.getTraineeRemarks());
	            }
	            hrDetails.setUpdatedBy(hrUnitInfo.getUpdatedBy());
	            hrDetails.setUpdatedDate(dateTime());
	            hrUnitRepo.save(hrDetails);
	        } catch (Exception e) {
	            log.error("Exception occured during saving data into hr table :  {}  ", e);
	        }
	    }

	
	

	/**
	 * validateInfo() this method is used for validating date
	 *
	 * @return boolean
	 */
	private boolean validateInfo(Date date) {
		if (date != null) {
			return true;
		}
		return false;
	}

	private boolean validateInfo(LocalDate date) {
		if (date != null) {
			return true;
		}
		return false;
	}

	/**
	 * validateInfo() this method is for validating any string type info
	 *
	 * @return boolean
	 */
	private boolean validateInfo(String info) {
		if (info != null && !info.isEmpty() && !info.equalsIgnoreCase("na") && !info.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * dateAndTime() will provide the current date and time
	 *
	 * @return Date
	 */
	private Date dateTime() {
		try {
			LocalDateTime dateTime = LocalDateTime.now();
			Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sm.format(date);
			return sm.parse(strDate);
		} catch (ParseException e) {
			log.error("Exception occured {} ", e);
		}
		return null;
	}

	private Date dateTimeFormat(Date date) {
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sm.format(date);
			return sm.parse(strDate);
		} catch (ParseException e) {
			log.error("Exception occured {} ", e);
		}
		return null;

	}

	@Override
	public UnitResponse searchTraineeData(TraineeDetails request) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName + " has been invoked... ");
		List<TraineeDetails> allHrData = null;
		try {
			Map<String, Object> hrValidData = getHRValidData(request);
			if (hrValidData != null && !hrValidData.keySet().isEmpty()) {
				if (hrValidData.containsKey("benMonth") && hrValidData.containsKey("traineeStatus")
						&& hrValidData.containsKey("benBench") && hrValidData.size() == 3) {
					List<HrUnitResponse> hrDataByMonthStatusBench = hrUnitRepo.getHrDataByMonthStatusBench(
							request.getBenchUnitData().getBenMonth(), String.valueOf(hrValidData.get("traineeStatus")),
							String.valueOf(hrValidData.get("benBench")));
					allHrData = getHrDataBasedOnCondition(hrDataByMonthStatusBench);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeBioMetricStatus") && hrValidData.containsKey("traineeICards")
						&& hrValidData.size() == 2) {
					List<HrUnitResponse> listOfhrDataByBiometricStatusAndIcards = hrUnitRepo
							.getHrDataByBiometricStatusAndIcards(
									String.valueOf(hrValidData.get("traineeBioMetricStatus")),
									String.valueOf(hrValidData.get("traineeICards")));
					allHrData = getHrDataBasedOnCondition(listOfhrDataByBiometricStatusAndIcards);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeStatus") && hrValidData.size() == 1) {
					try {
						List<HrUnitResponse> listOfHrDataByStatusGreaterThan5Months = hrUnitRepo
								.getHRDataByStatusGreaterThan5Months(String.valueOf(hrValidData.get("traineeStatus")));
						List<TraineeDetails> listOfHRUnit = new ArrayList<TraineeDetails>();
						if (listOfHrDataByStatusGreaterThan5Months != null
								&& listOfHrDataByStatusGreaterThan5Months.size() > 0) {
							for (HrUnitResponse hrDataOnCondition : listOfHrDataByStatusGreaterThan5Months) {
								TraineeDetails hrRequest = new TraineeDetails();
								HrUnitData hrUnit = new HrUnitData();
								BenchUnitData benchUnit = new BenchUnitData();
								TraineeData masterData = new TraineeData();
								hrRequest.setHrUnitData(hrUnit);
								hrRequest.setBenchUnitData(benchUnit);
								hrRequest.setTraineeData(masterData);
								BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getHrUnitData());
								BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getBenchUnitData());
								BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getTraineeData());
								int yy = hrRequest.getHrUnitData().getTraineeOnBoardingDate().getYear();
								int mm = hrRequest.getHrUnitData().getTraineeOnBoardingDate().getMonthValue();
								int dd = hrRequest.getHrUnitData().getTraineeOnBoardingDate().getDayOfMonth();
								Period between = Period.between(LocalDate.now(), LocalDate.of(yy, mm, dd));
								int months = between.getMonths();
								long years = between.getYears();
								long days = between.getDays();
								if (months * -1 >= 5 || years * -1 > 0) {
									listOfHRUnit.add(hrRequest);
								} else {
									log.info(methodName + " Employee didn't serve for more then 5 months . ");
								}
							}
						} else {
							log.info(methodName + " Data is not present in database");
						}
						if (listOfHRUnit.size() > 0) {
							return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
									.traineeDetailsList(listOfHRUnit).build();
						} else {
							return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
									.statusDesc("Failure").traineeDetailsList(listOfHRUnit).build();
						}

					} catch (Exception e) {
						log.error(methodName + " Exception Occurs {} ", e);
					}
				} else if (hrValidData.containsKey("traineeJoiningFormalities") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByTraineeJoining = hrUnitRepo
							.getHrDataByTraineeJoining(String.valueOf(hrValidData.get("traineeJoiningFormalities")));
					allHrData = getHrDataBasedOnCondition(hrDataByTraineeJoining);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("benBench") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByBenBench = hrUnitRepo
							.getHrDataByBenBench(String.valueOf(hrValidData.get("benBench")));
					allHrData = getHrDataBasedOnCondition(hrDataByBenBench);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeLOIFormalities") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByTraineeLoi = hrUnitRepo
							.getHrDataByTraineeLOIFormalities(String.valueOf(hrValidData.get("traineeLOIFormalities")));
					allHrData = getHrDataBasedOnCondition(hrDataByTraineeLoi);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeNDAFormalities") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByTraineeNda = hrUnitRepo
							.getHrDataByTraineeNDAFormalities(String.valueOf(hrValidData.get("traineeNDAFormalities")));
					allHrData = getHrDataBasedOnCondition(hrDataByTraineeNda);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeBioMetricStatus") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByTraineeBiometricStatus = hrUnitRepo.getHrDataByTraineeBioMetricStatus(
							String.valueOf(hrValidData.get("traineeBioMetricStatus")));
					allHrData = getHrDataBasedOnCondition(hrDataByTraineeBiometricStatus);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}
				} else if (hrValidData.containsKey("traineeICards") && hrValidData.size() == 1) {
					List<HrUnitResponse> hrDataByTraineeIcards = hrUnitRepo
							.getHrDataByTraineeIcards(String.valueOf(hrValidData.get("traineeICards")));
					allHrData = getHrDataBasedOnCondition(hrDataByTraineeIcards);
					if (allHrData != null && allHrData.size() != 0) {
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
								.traineeDetailsList(allHrData).build();
					} else {
						return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
								.statusDesc("Failed").build();
					}

				} else {
					log.info(methodName + " invalid HR data  ");
				}
			} else {
				log.info(methodName + " hrData after validation is null/ empty ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + " Exception Occurs {} ", e);
		}

		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Failure").build();
	}

	/**
	 * when getHRValidData() method is invoked, it returns the filtered data in key
	 * and value pair after the validation. Here the data present in benchUnit,
	 * masterData and hrUnit is get stored in Map and it is being filtered as per
	 * the condition and return the valid inputs which is stored in another Map.
	 * {Here property name is key and the properties' value is value}
	 *
	 * @param traineeDetails
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getHRValidData(TraineeDetails traineeDetails) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName + " has been invoked... ");

		Map<String, Object> maphrUnitRequest = null;
		Map<String, Object> mapHrUnitValidInputs = null;
		if (traineeDetails != null && (traineeDetails.getBenchUnitData() != null
				|| traineeDetails.getTraineeData() != null || traineeDetails.getHrUnitData() != null)) {
			maphrUnitRequest = new HashMap<String, Object>();
			mapHrUnitValidInputs = new HashMap<String, Object>();
			if (traineeDetails.getBenchUnitData() != null) {
				maphrUnitRequest.put("benBench", traineeDetails.getBenchUnitData().getBenBench());
				maphrUnitRequest.put("benMonth", traineeDetails.getBenchUnitData().getBenMonth());
			} else {
				log.info(" BenchUnit is null. . . ");
			}
			if (traineeDetails.getTraineeData() != null) {
				maphrUnitRequest.put("traineeStatus", traineeDetails.getTraineeData().getTraineeStatus());
			} else {
				log.info(" MasterData is null. . . ");
			}
			if (traineeDetails.getHrUnitData() != null) {
				maphrUnitRequest.put("traineeLOIFormalities",
						traineeDetails.getHrUnitData().getTraineeLOIFormalities());
				maphrUnitRequest.put("traineeNDAFormalities",
						traineeDetails.getHrUnitData().getTraineeNDAFormalities());
				maphrUnitRequest.put("traineeBioMetricStatus",
						traineeDetails.getHrUnitData().getTraineeBioMetricStatus());
				maphrUnitRequest.put("traineeICards", traineeDetails.getHrUnitData().getTraineeICards());
				maphrUnitRequest.put("traineeJoiningFormalities",
						traineeDetails.getHrUnitData().getTraineeJoiningFormalities());
			} else {
				log.info(" HrUnit is null. . . ");
			}
			Set<String> keySet = maphrUnitRequest.keySet();
			for (String key : keySet) {
				Object value = maphrUnitRequest.get(key);
				if (value != null && !value.equals("")) {
					mapHrUnitValidInputs.put(key, value);
				} else {
					log.info(" value before validation is null ");
				}
			}
		} else {
			log.info(" hrUnitRequest object is null ");
		}

		return mapHrUnitValidInputs;
	}

	/**
	 * This method is used to return HrUnitResponse as a response to the front-end .
	 *
	 * @param hrResponse
	 * @return HrUnitResponse
	 */

	public List<TraineeDetails> getHrDataBasedOnCondition(List<HrUnitResponse> hrResponse) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		List<TraineeDetails> listOfHRUnit = new ArrayList<TraineeDetails>();

		log.info(methodName + " has been invoked... ");
		try {
			if (hrResponse != null && hrResponse.size() > 0) {
				for (HrUnitResponse hrDataOnCondition : hrResponse) {
					TraineeDetails hrRequest = new TraineeDetails();
					HrUnitData hrUnit = new HrUnitData();
					BenchUnitData benchUnit = new BenchUnitData();
					TraineeData masterData = new TraineeData();
					hrRequest.setHrUnitData(hrUnit);
					hrRequest.setBenchUnitData(benchUnit);
					hrRequest.setTraineeData(masterData);
					BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getHrUnitData());
					BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getBenchUnitData());
					BeanUtils.copyProperties(hrDataOnCondition, hrRequest.getTraineeData());
					listOfHRUnit.add(hrRequest);
				}

			} else {
				log.info(methodName + " Data is not present in database");
			}

		} catch (Exception e) {
			log.error(methodName + " Exception Occurs {} ", e);
		}
		return listOfHRUnit;
	}
}

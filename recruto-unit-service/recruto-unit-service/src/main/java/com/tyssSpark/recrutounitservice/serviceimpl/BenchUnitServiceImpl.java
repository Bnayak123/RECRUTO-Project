package com.tyssSpark.recrutounitservice.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tyssSpark.recrutounitservice.dto.BenchUnitData;
import com.tyssSpark.recrutounitservice.dto.TraineeData;
import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import com.tyssSpark.recrutounitservice.repo.BenchUnitRepo;
import com.tyssSpark.recrutounitservice.repo.TraineeDataCustomRepo;
import com.tyssSpark.recrutounitservice.repo.TraineeDataRepo;
import com.tyssSpark.recrutounitservice.service.BenchUnitservice;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BenchUnitServiceImpl implements BenchUnitservice {
	@Autowired
	private BenchUnitRepo benchUnitRepo;
	@Autowired
	private TraineeDataRepo traineeDataRepo;
	@Autowired
	private TraineeDataCustomRepo traineeDataCustomRepo;

	/**
	 * Below method is used for adding the data in database which is coming from
	 * Bench UI page
	 */
	@Override
	public UnitResponse saveResources(TraineeDetails traineeDetails) {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		Date dateTime = dateTime();
		TraineeData traineeData;
		// loggers
		try {
			if (traineeDetails != null && traineeDetails.getBenchUnitData() != null
					&& traineeDetails.getTraineeData() != null) {
				if (traineeDetails.getBenchUnitData().getBenSource() != null
						&& !traineeDetails.getBenchUnitData().getBenSource().isBlank()
						
						&& !traineeDetails.getBenchUnitData().getBenSource().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenSource().equalsIgnoreCase("NA")
						&& traineeDetails.getBenchUnitData().getBenGender() != null
						&& !traineeDetails.getBenchUnitData().getBenGender().isBlank()
						
						&& !traineeDetails.getBenchUnitData().getBenGender().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenGender().equalsIgnoreCase("NA")
						&& traineeDetails.getBenchUnitData().getBenBench() != null
						&& !traineeDetails.getBenchUnitData().getBenBench().isEmpty()
						&& !traineeDetails.getBenchUnitData().getBenBench().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenBench().equalsIgnoreCase("NA")
						&& traineeDetails.getBenchUnitData().getBenBatch() != null
						&& !traineeDetails.getBenchUnitData().getBenBatch().isEmpty()
						&& !traineeDetails.getBenchUnitData().getBenBatch().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenBatch().equalsIgnoreCase("NA")
						&& traineeDetails.getBenchUnitData().getBenTraineeDuration() != null
						&& traineeDetails.getBenchUnitData().getBenTraineeDuration() >= 0

						&& traineeDetails.getBenchUnitData().getBenSkillSet() != null
						&& !traineeDetails.getBenchUnitData().getBenSkillSet().isEmpty()
						&& !traineeDetails.getBenchUnitData().getBenSkillSet().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenSkillSet().equalsIgnoreCase("NA")

						&& traineeDetails.getBenchUnitData().getBenCtc() != null
						&& !traineeDetails.getBenchUnitData().getBenCtc().isEmpty()
						&& !traineeDetails.getBenchUnitData().getBenCtc().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getBenCtc().equalsIgnoreCase("NA")

						&& traineeDetails.getBenchUnitData().getCreatedBy() != null
						&& !traineeDetails.getBenchUnitData().getCreatedBy().isEmpty()
						&& !traineeDetails.getBenchUnitData().getCreatedBy().equalsIgnoreCase("Null")
						&& !traineeDetails.getBenchUnitData().getCreatedBy().equalsIgnoreCase("NA")
						&&  traineeDetails.getTraineeData().getTraineeName() != null
						&& !traineeDetails.getTraineeData().getTraineeName().isEmpty()
						&& !traineeDetails.getTraineeData().getTraineeName().equalsIgnoreCase("Null")
						&& !traineeDetails.getTraineeData().getTraineeName().equalsIgnoreCase("NA")

						&& traineeDetails.getTraineeData().getTraineeEmailId() != null
						&& !traineeDetails.getTraineeData().getTraineeEmailId().isEmpty()
						&& !traineeDetails.getTraineeData().getTraineeEmailId().equalsIgnoreCase("Null")
						&& !traineeDetails.getTraineeData().getTraineeEmailId().equalsIgnoreCase("NA")

						&& traineeDetails.getTraineeData().getTraineeMobileNum() != null
						&& traineeDetails.getTraineeData().getTraineeMobileNum() > 0
						&& String.valueOf(traineeDetails.getTraineeData().getTraineeMobileNum()).length() == 10
						&& traineeDetails.getTraineeData().getTraineeYearOfExp() != null
						&& traineeDetails.getTraineeData().getTraineeYearOfExp() >= 0

						&& traineeDetails.getTraineeData().getCreatedBy() != null
						&& !traineeDetails.getTraineeData().getCreatedBy().isEmpty()
						&& !traineeDetails.getTraineeData().getCreatedBy().equalsIgnoreCase("Null")
						&& !traineeDetails.getTraineeData().getCreatedBy().equalsIgnoreCase("NA")) {
					traineeData = traineeDataRepo
							.findByTraineeEmailId(traineeDetails.getTraineeData().getTraineeEmailId());
					if (traineeData == null) {
						traineeDetails.getBenchUnitData().setCreatedDate(dateTime);
						traineeDetails.getTraineeData().setCreatedDate(dateTime);
						traineeDetails.getTraineeData().setTraineeStatus("Active");
						traineeDataRepo.save(traineeDetails.getTraineeData());
						traineeDetails.getBenchUnitData().setTraineeData(traineeDetails.getTraineeData());

						BenchUnitData benchUnit = traineeDetails.getBenchUnitData();
//                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//                        String strDate = sm.format(benchUnit.getBenMonth());
						benchUnitRepo.save(benchUnit);
						return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
								.statusDesc("Successfully data saved").build();

					} else {
						log.info(methodName + " data already present in database. ");
					}

				} else {

					log.info(methodName + " invalid benchUnit and masterData details. ");
				}
			} else {
				log.info(methodName + " invalid UserDataRequest details. ");
			}
		} catch (Exception e) {

			log.error(methodName + " Exception occurs during receiving object in : {} ", e);
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Failed to save data")
				.build();
	}

	@Override
	public UnitResponse editBenchUnit(TraineeDetails traineeDetails) {
		String methodName = BenchUnitServiceImpl.class.getSimpleName() + " " + new Object() {
		}.getClass().getEnclosingMethod().getName();

		TraineeData traineeDataInfo = null;
		BenchUnitData benchUnitInfo = null;
		try {
			log.info(methodName + " method has been invoked to edit details");
			if (traineeDetails != null) {

				TraineeData masterData = traineeDetails.getTraineeData();
				BenchUnitData benchUnit = traineeDetails.getBenchUnitData();

				boolean isBenchUnitModified = false;
				boolean isMasterUnitModified = false;

				if (masterData != null && masterData.getTraineeEmailId() != null
						&& !masterData.getTraineeEmailId().isEmpty() && (ValidateEmployeeStatus(masterData)
								|| masterData.getTraineeYearOfExp() != null || ValidateBenchUnit(benchUnit))) {

					traineeDataInfo = traineeDataRepo.findByTraineeEmailId(masterData.getTraineeEmailId());
					if (traineeDataInfo != null) {
						if (ValidateEmployeeStatus(masterData)) {
							traineeDataInfo.setTraineeStatus(masterData.getTraineeStatus());
							isMasterUnitModified = true;
						} else {
							log.info(methodName + "employee status is not updated in database");
						}
						if (masterData.getTraineeYearOfExp() != null && masterData.getTraineeYearOfExp() >=0) {
							traineeDataInfo.setTraineeYearOfExp(masterData.getTraineeYearOfExp());
							isMasterUnitModified = true;
						} else {
							log.info(methodName + "employee YoExp is not updated in database");
						}

						if (benchUnit != null && ValidateBenchUnit(benchUnit)) {
							benchUnitInfo = benchUnitRepo.findByTraineeData(traineeDataInfo);
							if (benchUnitInfo != null) {
								benchUnitInfo.setBenRevicedCtc(benchUnit.getBenRevicedCtc());
								benchUnitInfo.setBenRemarks(benchUnit.getBenRemarks());
								isBenchUnitModified = true;

							} else {
								log.info(methodName + "benchunit data is not present in database");
							}
						} else {
							log.info(
									methodName + "benchUnit is null or give both revisedCtc and remarks for benchUnit");
						}

						if (isBenchUnitModified && isMasterUnitModified) {
							if ((masterData.getUpdatedBy() != null && !masterData.getUpdatedBy().isEmpty() && !masterData.getUpdatedBy().equalsIgnoreCase("Null") && !masterData.getUpdatedBy().equalsIgnoreCase("NA") )
									&& (benchUnit.getUpdatedBy() != null && !benchUnit.getUpdatedBy().isEmpty() && !benchUnit.getUpdatedBy().equalsIgnoreCase("Null") && !benchUnit.getUpdatedBy().equalsIgnoreCase("NA") )) {
								benchUnitInfo.setUpdatedBy(benchUnit.getUpdatedBy());
								benchUnitInfo.setUpdatedDate(dateTime());
								benchUnitRepo.save(benchUnitInfo);
								traineeDataInfo.setUpdatedBy(masterData.getUpdatedBy());
								traineeDataInfo.setUpdatedDate(dateTime());
								traineeDataRepo.save(traineeDataInfo);
								return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
										.message("successfully updated").statusDesc("Success").build();
							}
						} else if (isMasterUnitModified && BenchUnitFalse(benchUnit)) {
							if (masterData.getUpdatedBy() != null && !masterData.getUpdatedBy().isEmpty() && !masterData.getUpdatedBy().equalsIgnoreCase("Null") && !masterData.getUpdatedBy().equalsIgnoreCase("NA")) {
								traineeDataInfo.setUpdatedBy(masterData.getUpdatedBy());
								traineeDataInfo.setUpdatedDate(dateTime());
								traineeDataRepo.save(traineeDataInfo);
								return UnitResponse.builder().statusCode(HttpStatus.OK + "")
										.message("successfully updated in MasterData").statusDesc("Success").build();
							}
						} else if (isBenchUnitModified) {
							if (benchUnit.getUpdatedBy() != null && !benchUnit.getUpdatedBy().isEmpty() && !benchUnit.getUpdatedBy().equalsIgnoreCase("Null") && !benchUnit.getUpdatedBy().equalsIgnoreCase("NA")) {
								benchUnitInfo.setUpdatedBy(benchUnit.getUpdatedBy());
								benchUnitInfo.setUpdatedDate(dateTime());
								benchUnitRepo.save(benchUnitInfo);
								return UnitResponse.builder().statusCode(HttpStatus.OK + "")
										.message("successfully updated in BenchUnit").statusDesc("Success").build();
							}
						}
					} else {
						log.info(methodName + " no data is present in database");
					}
				} else {
					log.info(methodName + " email is not valid");
				}
			} else {
				log.info(methodName + " userrequest is null");
			}
		} catch (Exception e) {
			log.error(methodName + " exception occured...!!");
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST + "").message("enter valid details")
				.statusDesc("failed").build();
	}

	/**
	 * for validating the employee status in masterData table
	 */
	private boolean ValidateEmployeeStatus(TraineeData masterStatus) {
		return masterStatus.getTraineeStatus() != null && !masterStatus.getTraineeStatus().isEmpty() && !masterStatus.getTraineeStatus().equalsIgnoreCase("Null") && !masterStatus.getTraineeStatus().equalsIgnoreCase("NA");
	}

	/**
	 * for validating the employee revisedCTC & remarks in benchUnit table
	 */
	private boolean ValidateBenchUnit(BenchUnitData benchUnit) {
		return benchUnit.getBenRevicedCtc() != null && !benchUnit.getBenRevicedCtc().isEmpty() && !benchUnit.getBenRevicedCtc().equalsIgnoreCase("Null") && !benchUnit.getBenRevicedCtc().equalsIgnoreCase("NA")
				&& benchUnit.getBenRemarks() != null && !benchUnit.getBenRemarks().isEmpty() && !benchUnit.getBenRemarks().equalsIgnoreCase("Null") && !benchUnit.getBenRemarks().equalsIgnoreCase("NA");
	}

	/**
	 * for getting the current date and time from system
	 */
	public Date dateTime() {
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

	/**
	 * for validating the employee revisedCTC & remarks is null or empty
	 */
	private boolean BenchUnitFalse(BenchUnitData benchUnit) {
		return (benchUnit.getBenRevicedCtc() == null || benchUnit.getBenRevicedCtc().isEmpty())
				&& (benchUnit.getBenRemarks() == null || benchUnit.getBenRemarks().isEmpty());
	}

	/**
	 * getAllData method is used to fetch all the data from the database
	 */
	@Override
	public UnitResponse getAllData() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		List<BenchUnitData> list = benchUnitRepo.findAllByOrderByBenMonthDesc();
		if (list != null && !list.isEmpty()) {
			log.info(methodName + "entered into the method");
			return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
					.benchUnitDataList(list).build();
		} else {
			log.error(methodName + " No data is present in database ");
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail").build();
	}

	/**
	 *
	 * getValidData() method is used to get the data from the user and validates it
	 * based on the values and it returns the valid data in key and value pair.
	 * {Here property name is key and the properties' value is value}
	 *
	 */

	private Map<String, Object> getValidData(TraineeDetails traineeDetails) {

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName, " has been invoked . ");

		Map<String, Object> mapUserRequest = null;
		Map<String, Object> mapValidInputs = null;

		if (traineeDetails != null
				&& (traineeDetails.getBenchUnitData() != null || traineeDetails.getTraineeData() != null)) {
			BenchUnitData benchUnit = traineeDetails.getBenchUnitData();
			TraineeData masterData = traineeDetails.getTraineeData();
			// changed from LinkedHashMap to HashMap
			mapUserRequest = new HashMap<String, Object>();
			mapValidInputs = new HashMap<String, Object>();
			if (traineeDetails.getTraineeData() != null) {
				mapUserRequest.put("traineeName", masterData.getTraineeName());
				mapUserRequest.put("traineeEmailId", masterData.getTraineeEmailId());
				mapUserRequest.put("traineeMobileNum", masterData.getTraineeMobileNum());
				mapUserRequest.put("traineeStatus", masterData.getTraineeStatus());
				mapUserRequest.put("traineeYearOfExp", masterData.getTraineeYearOfExp());
			}
			if (traineeDetails.getBenchUnitData() != null) {
				mapUserRequest.put("benMonth", benchUnit.getBenMonth());

			}
			Set<String> keySet = mapUserRequest.keySet();
			for (String key : keySet) {
				Object value = mapUserRequest.get(key);
				if (value != null && !value.equals("")) {
					mapValidInputs.put(key, mapUserRequest.get(key));
				}
			}
		} else {
			log.info(methodName, " method invoked with null parameter . ");
		}

		return mapValidInputs;
	}

	/**
	 * getBenchData() method is used to get the data from the database based on the
	 * parameters.
	 *
	 */

	@Override
	public UnitResponse getBenchData(TraineeDetails traineeDetails) {

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName + " has been invoked . ");

		List<BenchUnitData> searchData = null;

		try {
			if (traineeDetails != null) {
				Map<String, Object> validData = getValidData(traineeDetails);
				if (validData != null && !validData.keySet().isEmpty()) {
					if (validData.containsKey("benMonth") && validData.containsKey("traineeStatus")
							&& validData.size() == 2) {
						searchData = benchUnitRepo.getDataByMonthAndStatus(
								traineeDetails.getBenchUnitData().getBenMonth(),
								String.valueOf(validData.get("traineeStatus")));
						if (searchData.size() != 0)
							return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
									.benchUnitDataList(searchData).build();
						else
							return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
									.statusDesc("No Data Found").benchUnitDataList(searchData).build();
					} else if (validData.containsKey("benMonth") && validData.size() == 1) {
						searchData = benchUnitRepo.getDataByMonth(traineeDetails.getBenchUnitData().getBenMonth());
						if (searchData.size() != 0)
							return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
									.benchUnitDataList(searchData).build();
						else
							return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
									.statusDesc("No Data Found").benchUnitDataList(searchData).build();
					} else if (validData.containsKey("traineeEmailId") && validData.size() == 1
							|| validData.containsKey("traineeMobileNum") && validData.size() == 1
							|| validData.containsKey("traineeStatus") && validData.size() == 1) {
						searchData = traineeDataCustomRepo.getAllMasterData(validData);
						if (searchData.size() != 0)
							return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
									.benchUnitDataList(searchData).build();
						else
							return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
									.statusDesc("No Data Found").benchUnitDataList(searchData).build();
					}
				} else {
					log.info(methodName + " No valid data present . ");
				}
			} else {
				log.info(methodName + " method invoked with null parameter .");
			}
		} catch (Exception e) {
			log.error(methodName + " Exception Occurs : {}", e.getMessage());
		}
		return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail")
				.benchUnitDataList(searchData).build();
	}

	private Date dateFormat(Date date) {
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sm.format(date);
			return sm.parse(strDate);
		} catch (ParseException e) {
			log.error("Exception occured , { }", e);
		}
		return null;
	}
}

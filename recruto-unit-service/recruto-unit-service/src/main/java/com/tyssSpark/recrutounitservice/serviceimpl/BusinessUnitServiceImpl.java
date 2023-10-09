package com.tyssSpark.recrutounitservice.serviceimpl;

import com.tyssSpark.recrutounitservice.dto.*;
import com.tyssSpark.recrutounitservice.repo.BusinessUnitRepo;
import com.tyssSpark.recrutounitservice.repo.TraineeDataRepo;
import com.tyssSpark.recrutounitservice.service.BusinessUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Service
@Slf4j
public class BusinessUnitServiceImpl implements BusinessUnitService {
    @Autowired
    private TraineeDataRepo traineeDataRepo;
    @Autowired
    private BusinessUnitRepo businessUnitRepo;
    /**
     *
     * addDetails() gets an UserDataRequest and perform businessLogic ,stores in
     * DB,if the data of BusinessUnit Data is Already is present in DB it doesn't
     * store the data
     */
    @Override
    public UnitResponse addDetails(TraineeDetails userDataRequest) {
        String methodName = new BusinessUnitServiceImpl() {
        }.getClass().getEnclosingMethod().getName();
        try {
            if (userDataRequest != null && userDataRequest.getBusinessUnitData() != null
                    && userDataRequest.getTraineeData() != null
                    && userDataRequest.getTraineeData().getTraineeMobileNum() > 0 && String.valueOf(userDataRequest.getTraineeData().getTraineeMobileNum()).length()==10) {
                BusinessUnitData businessInfo = userDataRequest.getBusinessUnitData();
                TraineeData masterId = null;
                if (businessInfo.getEdfInitiated() != null && !businessInfo.getEdfInitiated().isEmpty() && !businessInfo.getEdfInitiated().equalsIgnoreCase("Null") && !businessInfo.getEdfInitiated().equalsIgnoreCase("NA")
                        && businessInfo.getClientName() != null && !businessInfo.getClientName().isEmpty() && !businessInfo.getClientName().equalsIgnoreCase("Null") && !businessInfo.getClientName().equalsIgnoreCase("NA")
                        && businessInfo.getBusinessUnitName() != null && !businessInfo.getBusinessUnitName().isEmpty() && !businessInfo.getBusinessUnitName().equalsIgnoreCase("Null") && !businessInfo.getBusinessUnitName().equalsIgnoreCase("NA")
                        && businessInfo.getReportingManagerName() != null && !businessInfo.getReportingManagerName().isEmpty() && !businessInfo.getReportingManagerName().equalsIgnoreCase("Null") && !businessInfo.getReportingManagerName().equalsIgnoreCase("NA")  
                        && businessInfo.getDeployementDate() != null
                        && businessInfo.getCreatedBy() != null && !businessInfo.getCreatedBy().isEmpty() && !businessInfo.getCreatedBy().equalsIgnoreCase("Null") &&  !businessInfo.getCreatedBy().equalsIgnoreCase("NA")) {
                    masterId = traineeDataRepo.findByTraineeMobileNum(userDataRequest.getTraineeData().getTraineeMobileNum());
                    if(masterId != null && masterId.getId() != null) {
                        BusinessUnitData businessData = businessUnitRepo.findByTraineeData(masterId);
                        if (businessData == null) {
                            businessInfo.setCreatedDate(dateTime());
                            businessInfo.setTraineeData(masterId);
                            businessUnitRepo.save(businessInfo);
                            return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
                                    .statusDesc("successfully data has to be saved in db").build();

                        } else {
                            log.info(methodName + " BusinesUnit data is already present in db");
                        }
                    } else {
                        log.info(methodName + " BusinessUnit data is not valid ");
                    }
                } else {
                    log.info(methodName + " Incomplete Data : {} ",businessInfo );
                }
            } else {
                log.info(methodName + " Empty Object / null data " , userDataRequest);
            }
        } catch (Exception e) {
            log.error(methodName + " Exception occurred during logging in : {} ", e);
        }
        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
                .statusDesc("Failed to saveData in DataBase").build();
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

    /**
     * getAllData method is used to fetch all the data of master table data,
     * business unit table data and some columns of hr unit table data from the
     * database
     */
    @Override
    public UnitResponse getAllData() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.info(methodName + "entered into the method");
        List<TraineeDetails> traineeDetailsList = null;
        try {
            List<BusinessUnitResponse> businessUnitResponseList = businessUnitRepo.findAllData();
            System.out.println(businessUnitResponseList);
            traineeDetailsList = SegregateBusinessObj(businessUnitResponseList);
            if(traineeDetailsList!=null) {
                return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
                        .traineeDetailsList(traineeDetailsList).build();
            }
        } catch (Exception e) {
            log.error(methodName + "Exception in logging into the getAllData : {}", e);
        }
        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Fail")
                .build();
    }

    /**
     * SegregateBusinessObj method is a private method which is used to segregate all
     * the data of master table data, business unit table data and some columns of
     * hr unit table data from the database
     */
    private List<TraineeDetails> SegregateBusinessObj(List<BusinessUnitResponse> businessUnitResponseList) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.info(methodName + "entered into the method");
        List<TraineeDetails> listData = null;
        if (businessUnitResponseList != null && !businessUnitResponseList.isEmpty()) {
            log.info(methodName + "BusinessDataInfo contains data");
            listData = new ArrayList<>();
            for (BusinessUnitResponse businessDataInfo : businessUnitResponseList) {
                TraineeDetails businessUnitResponse = new TraineeDetails();
                TraineeData candidateMaster = new TraineeData();
                HrUnitData hrUnit = new HrUnitData();
                BusinessUnitData businessUnit = new BusinessUnitData();
                BenchUnitData benchUnitData= new BenchUnitData();
                businessUnitResponse.setTraineeData(candidateMaster);
                businessUnitResponse.setHrUnitData(hrUnit);
                businessUnitResponse.setBusinessUnitData(businessUnit);
                businessUnitResponse.setBenchUnitData(benchUnitData);
                log.info(methodName + "required data has been set to businessUnitResponse");
                BeanUtils.copyProperties(businessDataInfo, businessUnitResponse.getBusinessUnitData());
                BeanUtils.copyProperties(businessDataInfo, businessUnitResponse.getHrUnitData());
                BeanUtils.copyProperties(businessDataInfo, businessUnitResponse.getTraineeData());
                BeanUtils.copyProperties(businessDataInfo, businessUnitResponse.getBenchUnitData());
                listData.add(businessUnitResponse);
            }

        } else {
            log.info(methodName + "BusinessUnitResponse is empty or null" + businessUnitResponseList);
        }

        return listData;
    }

    /**
     * editDetailsInBusinessAndMaster() method receives UserRequest object and use
     * it to edit the Data of Business and Master in DB
     *
     * @param userRequest
     * @return UnitResponse
     */

    @Override
    public UnitResponse editDetailsInBusinessAndMaster(TraineeDetails userRequest) {

        String methodName = BusinessUnitServiceImpl.class.getSimpleName() + " " + new BusinessUnitServiceImpl() {
        }.getClass().getEnclosingMethod().getName();

        TraineeData masterDataInfo = null;
        BusinessUnitData businessUnitInfo = null;

        boolean isMasterModified = false;
        boolean isBusinessModified = false;
        try {
            log.info("entered into the " + methodName + "method");
            if (userRequest != null && userRequest.getTraineeData() != null
                    && userRequest.getTraineeData().getTraineeMobileNum() > 0 && String.valueOf(userRequest.getTraineeData().getTraineeMobileNum()).length() == 10 ) {
                TraineeData masterData = userRequest.getTraineeData();
                BusinessUnitData businessUnit = userRequest.getBusinessUnitData();
                if (validateMaster(masterData)) {
                	System.out.println(masterData.getTraineeMobileNum());
                    masterDataInfo = traineeDataRepo.findByTraineeMobileNum(masterData.getTraineeMobileNum());
                    if (masterDataInfo != null) {
                        masterDataInfo.setTraineeStatus(masterData.getTraineeStatus());
                        masterDataInfo.setUpdatedDate(dateTime());
                        masterDataInfo.setUpdatedBy(masterData.getUpdatedBy());
                        isMasterModified = true;

                    } else {
                    	System.out.println(masterDataInfo);
                        log.error(methodName + "Data not present in Master DB");
                    }
                }
                if (validateBusiness(businessUnit)) {
                	System.out.println(masterData.getTraineeMobileNum());
                    businessUnitInfo = businessUnitRepo.getDataByTraineeMobileNo(masterData.getTraineeMobileNum());
                    System.out.println(businessUnitInfo);
                    if (businessUnitInfo != null) {
                        if (businessUnit.getEdfInitiated() != null && !businessUnit.getEdfInitiated().isEmpty()) {
                            businessUnitInfo.setEdfInitiated(businessUnit.getEdfInitiated());
                            isBusinessModified = true;
                        }

                        if (businessUnit.getClientName() != null && !businessUnit.getClientName().isEmpty()) {
                            businessUnitInfo.setClientName(businessUnit.getClientName());
                            isBusinessModified = true;
                        }

                        if (businessUnit.getBusinessUnitName() != null && !businessUnit.getBusinessUnitName().isEmpty()) {
                            businessUnitInfo.setBusinessUnitName(businessUnit.getBusinessUnitName());
                            isBusinessModified = true;
                        }

                        if (businessUnit.getReportingManagerName() != null
                                && !businessUnit.getReportingManagerName().isEmpty()) {
                            businessUnitInfo.setReportingManagerName(businessUnit.getReportingManagerName());
                            isBusinessModified = true;
                        }

                        if (businessUnit.getComments() != null && !businessUnit.getComments().isEmpty()) {
                            businessUnitInfo.setComments(businessUnit.getComments());
                            isBusinessModified = true;
                        }
                    } else {
                        log.error(methodName + "Data is not present in Business Unit DB");
                    }
                }
                if (isMasterModified && isBusinessModified) {
                    traineeDataRepo.save(masterDataInfo);

                    businessUnitInfo.setUpdatedBy(businessUnit.getUpdatedBy());
                    businessUnitInfo.setUpdatedDate(new Date());
                    businessUnitRepo.save(businessUnitInfo);
                    return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
                            .message("successfully updated in Master and Business Unit").statusDesc("Success").build();

                } else if (isMasterModified && validateBusinessNull(businessUnit)) {
                    traineeDataRepo.save(masterDataInfo);
                    return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
                            .message("successfully updated in Master").statusDesc("Success").build();

                } else if (isBusinessModified && masterData.getTraineeStatus() == null
                        || masterData.getTraineeStatus().isEmpty()) {
                    businessUnitInfo.setUpdatedBy(businessUnit.getUpdatedBy());
                    businessUnitInfo.setUpdatedDate(new Date());
                    businessUnitRepo.save(businessUnitInfo);
                    return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "")
                            .message("successfully updated in Business Unit").statusDesc("Success").build();
                }

            }
        } catch (Exception e) {
            log.error(methodName + "Exception occurs...!!!");
            e.printStackTrace();
        }
        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").message("enter valid detailss")
                .statusDesc("failed").build();
    }

    /*
     * for validating Master status in Master
     */
    private static boolean validateMaster(TraineeData masterData) {
        if (masterData.getTraineeStatus() != null && !masterData.getTraineeStatus().isEmpty() && !masterData.getTraineeStatus().equalsIgnoreCase("Null") && !masterData.getTraineeStatus().equalsIgnoreCase("Na")
                && masterData.getUpdatedBy() != null && !masterData.getUpdatedBy().isEmpty() && !masterData.getUpdatedBy().equalsIgnoreCase("Null") && !masterData.getUpdatedBy().equalsIgnoreCase("Na")) {
            return true;
        }
        return false;
    }

    /*
     * for validating the each and every property for Business unit
     */
    private static boolean validateBusiness(BusinessUnitData businessUnit) {
        if (businessUnit != null
                && ((businessUnit.getEdfInitiated() != null && !businessUnit.getEdfInitiated().isEmpty() && !businessUnit.getEdfInitiated().equalsIgnoreCase("Null") && !businessUnit.getEdfInitiated().equalsIgnoreCase("Na"))
                || (businessUnit.getClientName() != null && !businessUnit.getClientName().isEmpty() && !businessUnit.getClientName().equalsIgnoreCase("Null") && !businessUnit.getClientName().equalsIgnoreCase("Na"))
                || (businessUnit.getBusinessUnitName() != null && !businessUnit.getBusinessUnitName().isEmpty() && !businessUnit.getBusinessUnitName().equalsIgnoreCase("Null") && !businessUnit.getBusinessUnitName().equalsIgnoreCase("Na"))
                || (businessUnit.getReportingManagerName() != null
                && !businessUnit.getReportingManagerName().isEmpty() && !businessUnit.getReportingManagerName().equalsIgnoreCase("Null") && !businessUnit.getReportingManagerName().equalsIgnoreCase("Na"))
                || (businessUnit.getComments() != null && !businessUnit.getComments().isEmpty() && !businessUnit.getComments().equalsIgnoreCase("Null") && !businessUnit.getComments().equalsIgnoreCase("Na")))
                && (businessUnit.getUpdatedBy() != null && !businessUnit.getUpdatedBy().isEmpty() && !businessUnit.getUpdatedBy().equalsIgnoreCase("Null") && !businessUnit.getUpdatedBy().equalsIgnoreCase("Na"))) {
            return true;
        }
        return false;

    }

    /*
     * for checking the each and every property for Business unit is null
     */
    private static boolean validateBusinessNull(BusinessUnitData businessUnit) {
        if (businessUnit == null || ((businessUnit.getEdfInitiated() == null || businessUnit.getEdfInitiated().isEmpty())
                || (businessUnit.getClientName() == null || businessUnit.getClientName().isEmpty())
                || (businessUnit.getBusinessUnitName() == null || businessUnit.getBusinessUnitName().isEmpty())
                || (businessUnit.getReportingManagerName() == null || businessUnit.getReportingManagerName().isEmpty())
                || (businessUnit.getComments() == null || businessUnit.getComments().isEmpty()))) {
            return true;
        }
        return false;
    }

    /**
     * when getBusinessValidData() method is invoked, it returns the filtered data
     * in key and value pair after the validation. Here the data present in
     * masterData and hrUnit is get stored in Map and it is being filtered as per
     * the condition and return the valid inputs which is stored in another Map.
     * {Here property name is key and the properties' value is value}
     *
     * @param businessUnitRequest
     * @return Map<String, Object>
     */

    public Map<String, Object> getBusinessValidData(TraineeDetails businessUnitRequest) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.info(methodName + " has been invoked... ");

        Map<String, Object> mapBusinessUnitRequest = null;
        Map<String, Object> mapBusinessUnitValidInputs = null;

        if (businessUnitRequest != null
                && (businessUnitRequest.getTraineeData() != null || businessUnitRequest.getHrUnitData() != null)) {
            mapBusinessUnitRequest = new HashMap<>();
            mapBusinessUnitValidInputs = new HashMap<String, Object>();

            if (businessUnitRequest.getTraineeData() != null) {
                mapBusinessUnitRequest.put("traineeName", businessUnitRequest.getTraineeData().getTraineeName());
                mapBusinessUnitRequest.put("traineeMobileNum",
                        businessUnitRequest.getTraineeData().getTraineeMobileNum());
            } else {
                log.info(" MasterData is null. . . ");
            }
            if (businessUnitRequest.getHrUnitData() != null) {
                mapBusinessUnitRequest.put("traineeOfficialId", businessUnitRequest.getHrUnitData().getTraineeOfficialId());
                mapBusinessUnitRequest.put("traineeOfficialMailId",
                        businessUnitRequest.getHrUnitData().getTraineeOfficialMailId());
            } else {
                log.info(" HrUnit is null. . . ");
            }
            Set<String> keySet = mapBusinessUnitRequest.keySet();
            for (String key : keySet) {
                Object value = mapBusinessUnitRequest.get(key);
                if (value != null && !value.equals("")) {
                    mapBusinessUnitValidInputs.put(key, value);
                } else {
                    log.info(" value before validation is null ");
                }
            }
        } else {
            log.info(" businessUnitRequest object is null ");
        }
        return mapBusinessUnitValidInputs;
    }

    public UnitResponse getBusinessDataBasedOnCondition(List<BusinessUnitResponse> businessData) {

        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        log.info(methodName + " has been invoked... ");
        try {
            List<TraineeDetails> listOfBusinessUnit = new ArrayList<TraineeDetails>();
            if (businessData != null && businessData.size() > 0) {
                for (BusinessUnitResponse businessResponse : businessData) {
                    TraineeDetails businessUnitRequest = new TraineeDetails();
                    HrUnitData hrUnit = new HrUnitData();
                    TraineeData masterData = new TraineeData();
                    BusinessUnitData businessUnit = new BusinessUnitData();
                    businessUnitRequest.setHrUnitData(hrUnit);
                    businessUnitRequest.setTraineeData(masterData);
                    businessUnitRequest.setBusinessUnitData(businessUnit);
                    BeanUtils.copyProperties(businessResponse, businessUnitRequest.getHrUnitData());
                    BeanUtils.copyProperties(businessResponse, businessUnitRequest.getTraineeData());
                    BeanUtils.copyProperties(businessResponse, businessUnitRequest.getBusinessUnitData());

                    listOfBusinessUnit.add(businessUnitRequest);
                }

            } else {
                log.info(methodName + " Data is not present in database");
            }
            if (listOfBusinessUnit.size() > 0) {
                return UnitResponse.builder().statusCode(HttpStatus.OK.value() + "").statusDesc("Success")
                        .traineeDetailsList(listOfBusinessUnit).build();
            } else {
                return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "")
                        .statusDesc("Failure").traineeDetailsList(listOfBusinessUnit).build();
            }

        } catch (Exception e) {
            log.error(methodName + " Exception Occurs {} ", e);
        }
        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Failure")
                .build();
    }

    @Override
    public UnitResponse searchBusinessData(TraineeDetails businessUnitRequest) {

        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.info(methodName + " has been invoked... ");

        UnitResponse allBusinessData = null;
        try {
            Map<String, Object> businessValidData = getBusinessValidData(businessUnitRequest);
            if (businessValidData != null && !businessValidData.keySet().isEmpty()) {
                if (businessValidData.containsKey("traineeName") && businessValidData.size() == 1) {
                    List<BusinessUnitResponse> businessDataByName = businessUnitRepo.getBusinessDataByName(String.valueOf(businessValidData.get("traineeName")));
                    allBusinessData = getBusinessDataBasedOnCondition(businessDataByName);
                    return allBusinessData;
                } else if (businessValidData.containsKey("traineeMobileNum") && businessValidData.size() == 1) {
                    List<BusinessUnitResponse> businessDataByName = businessUnitRepo.getBusinessDataByPhone(
                            Long.valueOf(String.valueOf(businessValidData.get("traineeMobileNum"))));
                    allBusinessData = getBusinessDataBasedOnCondition(businessDataByName);
                    return allBusinessData;
                } else if (businessValidData.containsKey("traineeOfficialId") && businessValidData.size() == 1) {
                    List<BusinessUnitResponse> businessDataByName = businessUnitRepo
                            .getBusinessDataByOfficialId(String.valueOf(businessValidData.get("traineeOfficialId")));
                    allBusinessData = getBusinessDataBasedOnCondition(businessDataByName);
                    return allBusinessData;
                } else if (businessValidData.containsKey("traineeOfficialMailId") && businessValidData.size() == 1) {
                    List<BusinessUnitResponse> businessDataByName = businessUnitRepo
                            .getBusinessDataByMailId(String.valueOf(businessValidData.get("traineeOfficialMailId")));
                    allBusinessData = getBusinessDataBasedOnCondition(businessDataByName);
                    return allBusinessData;
                }
            } else {
                log.error(methodName + "Enter Valid data to search");
            }
        } catch (Exception e) {
            log.error(methodName + " Exception Occurs {} ", e);
        }

        return UnitResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value() + "").statusDesc("Failure")
                .build();
    }
}

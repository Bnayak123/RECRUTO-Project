package com.tyssSpark.recrutounitservice.repo;

import com.tyssSpark.recrutounitservice.dto.BusinessUnitData;
import com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse;
import com.tyssSpark.recrutounitservice.dto.TraineeData;
import com.tyssSpark.recrutounitservice.dto.TraineeDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface BusinessUnitRepo extends JpaRepository<BusinessUnitData, Integer> {

//    BusinessUnitData findByTraineeDataId(TraineeData masterId);

    @Query("select new com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse(buUnit.edfInitiated,buUnit.deployementDate,buUnit.clientName,buUnit.businessUnitName,"
            + "buUnit.reportingManagerName,buUnit.comments,master.traineeName,master.traineeEmailId,master.traineeMobileNum,master.traineeStatus,"
            + "master.traineeYearOfExp,hrUnit.traineeOfficialId,hrUnit.traineeOnBoardingDate,hrUnit.traineeOfficialMailId, bench.benGender)"
            + "from BusinessUnitData buUnit, HrUnitData hrUnit, BenchUnitData bench ,TraineeData master where buUnit.traineeData.id=master.id "
            + "and hrUnit.traineeData.id=master.id and bench.traineeData.id=master.id and master.traineeStatus = 'RFD'")
    public List<BusinessUnitResponse> findAllData();

    /**
     * findByMasterDataId() this method is used to find the instance of BusinessUnit
     * from BusinessUnitDB based on MasterData
     * @param masterId
     * @return BusinessUnit
     */
    BusinessUnitData findByTraineeData(TraineeData masterId);

    /**
     * findByMasterMobileNo() this method is used to find the instance of BusinessUnit
     * from BusinessUnitDB based on MasterData Mobile Number using JPQL
     * @param phoneNumber
     * @return BusinessUnit
     */
//    @Query("from TraineeData traineeData,BusinessUnitData businessUnit where traineeData.traineeMobileNum=:phoneNumber")
//    public BusinessUnitData findByTraineeMobileNo(Long phoneNumber);
    
    @Query("from BusinessUnitData businessUnit where businessUnit.traineeData.traineeMobileNum=:phoneNumber")
    public BusinessUnitData getDataByTraineeMobileNo(Long phoneNumber);

    @Query("select new com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse(" +
            "buUnit.edfInitiated,buUnit.deployementDate,buUnit.clientName,buUnit.businessUnitName," +
            "buUnit.reportingManagerName,buUnit.comments,master.traineeName,master.traineeEmailId,master.traineeMobileNum,master.traineeStatus," +
            "master.traineeYearOfExp,hrUnit.traineeOfficialId,hrUnit.traineeOnBoardingDate,hrUnit.traineeOfficialMailId ) " +
            "from BusinessUnitData buUnit, HrUnitData hrUnit, TraineeData master where " +
            "master.id = hrUnit.traineeData.id and master.id = buUnit.traineeData.id and " +
            "master.traineeName = :empName")
    List<BusinessUnitResponse> getBusinessDataByName(String empName);

    @Query("select new com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse(" +
            "buUnit.edfInitiated,buUnit.deployementDate,buUnit.clientName,buUnit.businessUnitName," +
            "buUnit.reportingManagerName,buUnit.comments,master.traineeName,master.traineeEmailId,master.traineeMobileNum,master.traineeStatus," +
            "master.traineeYearOfExp,hrUnit.traineeOfficialId,hrUnit.traineeOnBoardingDate,hrUnit.traineeOfficialMailId  ) " +
            "from BusinessUnitData buUnit, HrUnitData hrUnit, TraineeData master where " +
            "master.id = hrUnit.traineeData.id and master.id = buUnit.traineeData.id and " +
            "master.traineeMobileNum = :empPhone")
    List<BusinessUnitResponse> getBusinessDataByPhone(Long empPhone );

    @Query("select new com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse(" +
            "buUnit.edfInitiated,buUnit.deployementDate,buUnit.clientName,buUnit.businessUnitName," +
            "buUnit.reportingManagerName,buUnit.comments,master.traineeName,master.traineeEmailId,master.traineeMobileNum,master.traineeStatus," +
            "master.traineeYearOfExp,hrUnit.traineeOfficialId,hrUnit.traineeOnBoardingDate,hrUnit.traineeOfficialMailId   ) " +
            "from BusinessUnitData buUnit, HrUnitData hrUnit, TraineeData master where " +
            "master.id = hrUnit.traineeData.id and master.id = buUnit.traineeData.id and " +
            "hrUnit.traineeOfficialId = :empOfficalId")
    List<BusinessUnitResponse> getBusinessDataByOfficialId(String empOfficalId);

    @Query("select new com.tyssSpark.recrutounitservice.dto.BusinessUnitResponse(" +
            "buUnit.edfInitiated,buUnit.deployementDate,buUnit.clientName,buUnit.businessUnitName," +
            "buUnit.reportingManagerName,buUnit.comments,master.traineeName,master.traineeEmailId,master.traineeMobileNum,master.traineeStatus," +
            "master.traineeYearOfExp,hrUnit.traineeOfficialId,hrUnit.traineeOnBoardingDate,hrUnit.traineeOfficialMailId    )" +
            "from BusinessUnitData buUnit, HrUnitData hrUnit, TraineeData master " +
            "where master.id = hrUnit.traineeData.id and master.id = buUnit.traineeData.id " +
            "and hrUnit.traineeOfficialMailId = :empMailId")
    List<BusinessUnitResponse> getBusinessDataByMailId(String empMailId);

}

package com.tyssSpark.recrutounitservice.service;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;


public interface BusinessUnitService {
    /*
     * @param userDataRequest
     * @return UserAuthResponse
     * return response from the BusinessUnitServiceImpl and pass to BusinessUnitController
     */
    UnitResponse addDetails(TraineeDetails userDataRequest);

    UnitResponse getAllData();

    /**
     * editDetailsInBusinessAndMaster() this is a abstract method which is implemented in BusinessServiceImpl
     * @param userRequest
     * @return UnitAuthResponse
     */
    public UnitResponse editDetailsInBusinessAndMaster(TraineeDetails userRequest);

    UnitResponse searchBusinessData(TraineeDetails businessUnitRequest);
}

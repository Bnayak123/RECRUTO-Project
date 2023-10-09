package com.tyssSpark.recrutounitservice.service;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;


public interface HrUnitService {
    public UnitResponse onBoardTrainee(TraineeDetails traineeDetails);

    public UnitResponse getAllTraineeData();

    public UnitResponse editTraineeDetails(TraineeDetails traineeDetails);

    public UnitResponse searchTraineeData(TraineeDetails request);
}

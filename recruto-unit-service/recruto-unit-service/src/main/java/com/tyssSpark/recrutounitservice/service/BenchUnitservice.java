package com.tyssSpark.recrutounitservice.service;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface BenchUnitservice {
    public UnitResponse getAllData();

    public UnitResponse saveResources(TraineeDetails traineeDetails);

   public UnitResponse editBenchUnit(TraineeDetails traineeDetails);

    public UnitResponse getBenchData(TraineeDetails traineeDetails);
}

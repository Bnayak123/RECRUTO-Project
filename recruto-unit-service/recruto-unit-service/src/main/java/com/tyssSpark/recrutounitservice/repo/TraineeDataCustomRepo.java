package com.tyssSpark.recrutounitservice.repo;

import com.tyssSpark.recrutounitservice.dto.BenchUnitData;

import java.util.List;
import java.util.Map;


public interface TraineeDataCustomRepo {

    List<BenchUnitData> getAllMasterData(Map<String, Object> validData);
}

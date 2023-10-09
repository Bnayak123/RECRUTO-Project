package com.tyssSpark.recrutounitservice.repo;

import com.tyssSpark.recrutounitservice.dto.TraineeData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TraineeDataRepo extends JpaRepository<TraineeData,Integer> {

   public TraineeData findByTraineeEmailId(String traineeEmailId);

    TraineeData findByTraineeMobileNum(Long traineeMobileNum);

}

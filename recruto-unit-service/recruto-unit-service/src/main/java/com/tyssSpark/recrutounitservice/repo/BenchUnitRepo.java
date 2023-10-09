package com.tyssSpark.recrutounitservice.repo;

import com.tyssSpark.recrutounitservice.dto.BenchUnitData;
import com.tyssSpark.recrutounitservice.dto.TraineeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface BenchUnitRepo extends JpaRepository<BenchUnitData, Integer> {
	public List<BenchUnitData> findAllByOrderByBenMonthDesc();

	public BenchUnitData findByTraineeData(TraineeData traineeData);

	@Query("From BenchUnitData where benMonth=:month and traineeData.traineeStatus=:status")
	public List<BenchUnitData> getDataByMonthAndStatus(LocalDate month, String status);

	@Query("From BenchUnitData where benMonth=:month")
	public List<BenchUnitData> getDataByMonth(LocalDate month);

	@Query("From BenchUnitData where traineeData.id=:masterDataId")
	public BenchUnitData getMasterData(Integer masterDataId);
}

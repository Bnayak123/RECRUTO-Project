package com.tyssSpark.recrutounitservice.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraineeDetails {
    private TraineeData traineeData;
    private BenchUnitData benchUnitData;
    private HrUnitData hrUnitData;
    private BusinessUnitData businessUnitData;
}

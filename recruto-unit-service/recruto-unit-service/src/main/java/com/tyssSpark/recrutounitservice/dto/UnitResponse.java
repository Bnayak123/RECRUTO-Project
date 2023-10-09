package com.tyssSpark.recrutounitservice.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitResponse {
    private String statusCode;
    private String statusDesc;
    private String message;
    private List<BenchUnitData> benchUnitDataList;
    private List<TraineeDetails> traineeDetailsList;
    private List< List<HrUnitResponse>> hrUnitResponses;
}

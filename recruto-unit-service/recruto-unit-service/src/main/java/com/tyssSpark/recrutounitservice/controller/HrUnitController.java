package com.tyssSpark.recrutounitservice.controller;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import com.tyssSpark.recrutounitservice.dto.UserAuth;
import com.tyssSpark.recrutounitservice.service.HrUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/service/v1.0/unit/hr")
public class HrUnitController {

    @Autowired
    private HrUnitService hrUnitService;

    /**
     *
     * @param traineeDetails
     * @return UnitResponse
     * This method  send the request to the Hrunitservice and receive the response form the service
     */
    @PostMapping("/save")
    public UnitResponse onBoardTrainee(@RequestBody TraineeDetails traineeDetails) {
        return hrUnitService.onBoardTrainee(traineeDetails);
    }
    /**
     * getData() is used to get all by taking request from UI and invoke Service
     * layer to execute business logic
     */
    @GetMapping("/getalldata")
    public UnitResponse getAllTraineeData() {
        return hrUnitService.getAllTraineeData();
    }

    /**
     * editDetails() this method send the UserRequest object to the HRService and receives the UnitAuthResponse from HRService
     * @param traineeDetails
     * @return UnitResponse
     */
    @PostMapping("/edit")
    public UnitResponse editTraineeDetails(@RequestBody TraineeDetails traineeDetails)
    {
        return hrUnitService.editTraineeDetails(traineeDetails);
    }
    @PostMapping("/search/details")
    public UnitResponse searchTraineeData(@RequestBody TraineeDetails request){
        return hrUnitService.searchTraineeData(request) ;
    }
}

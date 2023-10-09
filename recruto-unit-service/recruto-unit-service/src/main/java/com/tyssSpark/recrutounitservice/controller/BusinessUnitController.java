package com.tyssSpark.recrutounitservice.controller;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import com.tyssSpark.recrutounitservice.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/service/v1.0/unit/business")
public class BusinessUnitController {

    @Autowired
    BusinessUnitService businessUnitService;
    /**
     * @param userDataRequest
     * @return UnitResponse
     */
    @PostMapping("/save")
    public UnitResponse addDetails(@RequestBody TraineeDetails userDataRequest) {
        return businessUnitService.addDetails(userDataRequest);

    }

    /**
     * getAllData() is used to get all by taking request from UI and invoke Service
     * layer to execute business logic
     */
    @GetMapping("/getAll")
    public UnitResponse getAllData() {
        return businessUnitService.getAllData(); 
    }

    /**
     * editDetails() this method send the UserRequest object to the BusinessService
     * and receives the UnitResponse from BusinessService
     *
     * @param userRequest
     * @return unitAuthResponse
     */

    @PostMapping("/edit")
    public UnitResponse editDetails(@RequestBody TraineeDetails userRequest) {
        return businessUnitService.editDetailsInBusinessAndMaster(userRequest);
    }

    /**
     * searchBusinessData() method is used to get all the data from the businessUnit along with
     * the benchUnit and masterData.
     *
     * @param businessUnitRequest
     * @return BusinessUnitResponse
     */

    @PostMapping("/search")
    public UnitResponse searchBusinessData(@RequestBody TraineeDetails businessUnitRequest) {
        UnitResponse response = businessUnitService.searchBusinessData(businessUnitRequest);
        return response;
    }
}

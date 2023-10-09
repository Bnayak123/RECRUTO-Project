package com.tyssSpark.recrutounitservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyssSpark.recrutounitservice.dto.TraineeDetails;
import com.tyssSpark.recrutounitservice.dto.UnitResponse;
import com.tyssSpark.recrutounitservice.service.BenchUnitservice;

import lombok.extern.slf4j.Slf4j;




@RestController
@CrossOrigin
@RequestMapping("/service/v1.0/unit/bench")
public class BenchUnitController {
    @Autowired
    private BenchUnitservice benchUnitservice;
    
    @PostMapping("/save")
    public UnitResponse saveTrainee(@RequestBody TraineeDetails traineeDetails) {
        return benchUnitservice.saveResources(traineeDetails);
    }
    @GetMapping("/getalldata")
    public UnitResponse getData() {
        return benchUnitservice.getAllData();
    }

    @PostMapping("/edit")
    public UnitResponse editDetails(@RequestBody TraineeDetails traineeDetails) {
        return benchUnitservice.editBenchUnit(traineeDetails);
    }

    @PostMapping("/search/details")
    public UnitResponse searchData(@RequestBody TraineeDetails request){
        return benchUnitservice.getBenchData(request) ;
    }

}

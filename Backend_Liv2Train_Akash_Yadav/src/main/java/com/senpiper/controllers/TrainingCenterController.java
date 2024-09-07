package com.senpiper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senpiper.dtos.TrainingCenterReqDto;
import com.senpiper.exceptions.TrainingCenterException;
import com.senpiper.model.TrainingCenter;
import com.senpiper.services.TrainingCenterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/senpiper")
public class TrainingCenterController {

    @Autowired
    private TrainingCenterService trainingCenterService;

    @PostMapping("/register-training-center")
    public ResponseEntity<TrainingCenter> createTrainingCenter(@Valid @RequestBody TrainingCenterReqDto dto) throws TrainingCenterException {
        TrainingCenter createdCenter = trainingCenterService.registerTrainingCenter(dto);
        return new ResponseEntity<>(createdCenter, HttpStatus.CREATED);
    }

    @GetMapping("/training-centers")
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenters() {
        List<TrainingCenter> centers = trainingCenterService.getAllTrainingCenters();
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }
}

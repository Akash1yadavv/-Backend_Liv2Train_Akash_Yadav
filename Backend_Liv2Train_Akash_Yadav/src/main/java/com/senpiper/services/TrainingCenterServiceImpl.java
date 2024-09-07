package com.senpiper.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senpiper.dtos.TrainingCenterReqDto;
import com.senpiper.exceptions.TrainingCenterException;
import com.senpiper.model.TrainingCenter;
import com.senpiper.repositories.TrainingCenterRepository;

@Service
public class TrainingCenterServiceImpl implements TrainingCenterService{
	
    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

	@Override
	public TrainingCenter registerTrainingCenter(TrainingCenterReqDto dto) throws TrainingCenterException {
		// Check if a training center with the same CenterCode already exists
        Optional<TrainingCenter> existingCenter = trainingCenterRepository.findByCenterCode(dto.getCenterCode());

        if (existingCenter.isPresent()) {
            throw new TrainingCenterException("Training center with CenterCode " + dto.getCenterCode() + " already exists.");
        }

        // Map DTO to Entity
        TrainingCenter trainingCenter = new TrainingCenter();
        trainingCenter.setCenterName(dto.getCenterName());
        trainingCenter.setCenterCode(dto.getCenterCode());
        trainingCenter.setAddress(dto.getAddress());
        trainingCenter.setStudentCapacity(dto.getStudentCapacity());
        trainingCenter.setCoursesOffered(dto.getCoursesOffered());
        trainingCenter.setContactEmail(dto.getContactEmail());
        trainingCenter.setContactPhone(dto.getContactPhone());
        
        // Save the new training center
        return trainingCenterRepository.save(trainingCenter);
	}

	@Override
	public List<TrainingCenter> getAllTrainingCenters() throws TrainingCenterException {
        List<TrainingCenter> centers = trainingCenterRepository.findAll();

        // Return an empty list if no centers are found
        return centers.isEmpty() ? new ArrayList<>() : centers;
	}

}

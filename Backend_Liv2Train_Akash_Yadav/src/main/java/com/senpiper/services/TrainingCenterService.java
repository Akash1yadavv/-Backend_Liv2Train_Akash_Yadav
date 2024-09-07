package com.senpiper.services;

import java.util.List;

import com.senpiper.dtos.TrainingCenterReqDto;
import com.senpiper.exceptions.TrainingCenterException;
import com.senpiper.model.TrainingCenter;

public interface TrainingCenterService {
	
	TrainingCenter registerTrainingCenter(TrainingCenterReqDto dto) throws TrainingCenterException;
	List<TrainingCenter> getAllTrainingCenters() throws TrainingCenterException;
}

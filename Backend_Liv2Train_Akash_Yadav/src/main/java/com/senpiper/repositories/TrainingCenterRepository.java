package com.senpiper.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senpiper.model.TrainingCenter;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {
	Optional<TrainingCenter> findByCenterCode(String centerCode);
    // Filter by Center Name (Optional)
    List<TrainingCenter> findByCenterNameContaining(String centerName);
    
    // Filter by City (Optional)
    List<TrainingCenter> findByAddress_City(String city);

}

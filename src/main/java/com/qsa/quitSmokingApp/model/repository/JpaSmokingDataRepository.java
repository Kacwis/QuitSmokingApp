package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.SmokingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSmokingDataRepository extends JpaRepository<SmokingData, Integer>, SmokingDataRepository {
}

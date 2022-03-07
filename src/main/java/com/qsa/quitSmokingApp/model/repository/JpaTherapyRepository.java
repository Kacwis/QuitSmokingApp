package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTherapyRepository extends JpaRepository<Therapy, Integer>, TherapyRepository {
}

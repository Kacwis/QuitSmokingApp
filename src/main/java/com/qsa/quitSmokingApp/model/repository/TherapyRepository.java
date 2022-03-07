package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.Therapy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TherapyRepository {

    List<Therapy> findAll();

    Page<Therapy> findAll(Pageable pageable);

    Optional<Therapy> findById(Integer id);

    Therapy save(Therapy appUser);

    boolean existsById(Integer id);
}

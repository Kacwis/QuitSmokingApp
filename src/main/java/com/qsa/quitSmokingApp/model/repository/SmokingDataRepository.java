package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.SmokingData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SmokingDataRepository {

    List<SmokingData> findAll();

    Page<SmokingData> findAll(Pageable pageable);

    Optional<SmokingData> findById(Integer id);

    SmokingData save(SmokingData smokingData);

    boolean existsById(Integer id);
}

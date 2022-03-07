package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    List<AppUser> findAll();

    Page<AppUser> findAll(Pageable pageable);

    Optional<AppUser> findById(Integer id);

    AppUser save(AppUser appUser);

    boolean existsById(Integer id);

}

package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<AppUser, Integer>, UserRepository {
}

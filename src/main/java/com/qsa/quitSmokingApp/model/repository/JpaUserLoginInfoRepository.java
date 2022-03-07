package com.qsa.quitSmokingApp.model.repository;

import com.qsa.quitSmokingApp.model.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserLoginInfoRepository extends JpaRepository<UserLoginInfo, Integer>, UserLoginInfoRepository {
}

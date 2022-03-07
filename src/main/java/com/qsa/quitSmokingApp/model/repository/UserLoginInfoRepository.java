package com.qsa.quitSmokingApp.model.repository;


import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.UserLoginInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserLoginInfoRepository {

    List<UserLoginInfo> findAll();

    Page<UserLoginInfo> findAll(Pageable pageable);

    Optional<UserLoginInfo> findById(Integer id);

    UserLoginInfo save(UserLoginInfo userLoginInfo);

    boolean existsById(Integer id);
}

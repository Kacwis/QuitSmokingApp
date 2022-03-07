package com.qsa.quitSmokingApp.logic;


import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.projection.AppUserReadModel;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppUserService {

    private SmokingDataRepository smokingDataRepository;
    private UserLoginInfoRepository userLoginInfoRepository;
    private UserRepository userRepository;
    private LoginInfoService loginInfoService;

    AppUserService(final SmokingDataRepository smokingDataRepository,
                   final UserLoginInfoRepository userLoginInfoRepository,
                   final LoginInfoService loginInfoService,
                   final UserRepository userRepository){

        this.smokingDataRepository = smokingDataRepository;
        this.userLoginInfoRepository = userLoginInfoRepository;
        this.loginInfoService = loginInfoService;
        this.userRepository = userRepository;
    }

    public AppUser createNewUser(NewUserWriteModel newUserWriteModel){
        int averageSleepingTime = newUserWriteModel.getAverageSleepingTime();
        Date dateOfBirth = newUserWriteModel.getDateOfBirth();
        String gender = newUserWriteModel.getGender();
        var appUser = new AppUser();
        appUser.setAverageSleepingTime(averageSleepingTime);
        appUser.setGender(gender);
        appUser.setDateOfBirth(dateOfBirth);
        var appUserResult = userRepository.save(appUser);

        String login = newUserWriteModel.getLogin();
        String password = newUserWriteModel.getPassword();
        var userLoginInfoResult = loginInfoService.getUserLoginInfo(login, password);
        userLoginInfoResult.setAppUser(appUserResult);
        var userLoginInfoRepositoryResult = userLoginInfoRepository.save(userLoginInfoResult);

        int ageStartedSmoking = newUserWriteModel.getAgeStartedSmoking();
        int cigarettesPerDay = newUserWriteModel.getCigarettesPerDay();
        String therapyMode = newUserWriteModel.getTherapyMode();
        var smokingDataResult = new SmokingData();
        smokingDataResult.setTherapyMode(therapyMode);
        smokingDataResult.setAgeStartedSmoking(ageStartedSmoking);
        smokingDataResult.setCigarettesPerDay(cigarettesPerDay);
        smokingDataResult.setAppUser(appUserResult);
        var smokingDataRepositoryResult = smokingDataRepository.save(smokingDataResult);
        appUserResult.setSmokingInfo(smokingDataRepositoryResult);
        appUserResult.setUserLoginInfo(userLoginInfoRepositoryResult);
        return appUser;
    }

}

package com.qsa.quitSmokingApp.logic;


import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.UserLoginInfo;
import com.qsa.quitSmokingApp.model.projection.AppUserReadModel;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        var toSaveAppUser = getNewAppUser(newUserWriteModel);
        var appUserResult = userRepository.save(toSaveAppUser);
        var toSaveLoginInfo = getUserLoginInfo(newUserWriteModel);
        toSaveLoginInfo.setAppUser(appUserResult);
        var userLoginInfoRepositoryResult = userLoginInfoRepository.save(toSaveLoginInfo);
        var smokingDataResult = getSmokingData(newUserWriteModel);
        smokingDataResult.setAppUser(appUserResult);
        var smokingDataRepositoryResult = smokingDataRepository.save(smokingDataResult);
        appUserResult.setSmokingData(smokingDataRepositoryResult);
        appUserResult.setUserLoginInfo(userLoginInfoRepositoryResult);
        return appUserResult;
    }

    public List<AppUser> getAllUsers(){
        var result = userRepository.findAll();
        return result;
    }

    public AppUser getUserById(int id){
        var result = userRepository.findById(id);
        if(result.isEmpty())
            return null;
        return result.get();
    }

    private AppUser getNewAppUser(NewUserWriteModel newUserWriteModel){
        int averageSleepingTime = newUserWriteModel.getAverageSleepingTime();
        LocalDate dateOfBirth = newUserWriteModel.getDateOfBirth();
        String gender = newUserWriteModel.getGender();
        var appUser = new AppUser();
        appUser.setAverageSleepingTime(averageSleepingTime);
        appUser.setGender(gender);
        appUser.setDateOfBirth(dateOfBirth);
        return appUser;
    }

    private UserLoginInfo getUserLoginInfo(NewUserWriteModel newUserWriteModel){
        String login = newUserWriteModel.getLogin();
        String password = newUserWriteModel.getPassword();
        var userLoginInfoResult = loginInfoService.getUserLoginInfo(login, password);
        return userLoginInfoResult;
    }

    private SmokingData getSmokingData(NewUserWriteModel newUserWriteModel){
        int ageStartedSmoking = newUserWriteModel.getAgeStartedSmoking();
        int cigarettesPerDay = newUserWriteModel.getCigarettesPerDay();
        String therapyMode = newUserWriteModel.getTherapyMode();
        var smokingDataResult = new SmokingData();
        smokingDataResult.setTherapyMode(therapyMode);
        smokingDataResult.setAgeStartedSmoking(ageStartedSmoking);
        smokingDataResult.setCigarettesPerDay(cigarettesPerDay);
        return smokingDataResult;
    }

}

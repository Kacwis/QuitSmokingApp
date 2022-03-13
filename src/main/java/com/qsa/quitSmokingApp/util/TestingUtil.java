package com.qsa.quitSmokingApp.util;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;

import java.time.LocalDate;
import java.util.ArrayList;

public final class TestingUtil {

    private TestingUtil(){}


    public static AppUser createAppUserSample(int id){
        var appUser = new AppUser();
        appUser.setDateOfBirth(LocalDate.parse("2012-10-02"));
        appUser.setGender("Male");
        appUser.setAverageSleepingTime(10);
        appUser.setId(id);
        appUser.setTherapies(new ArrayList<>());
        return appUser;
    }

    public static NewUserWriteModel createNewUserWriteModelSample(AppUser appUser) {
        int ageStartedSmoking = 20,
                cigarettesPerDay = 20;
        String login = "login",
                password = "password",
                therapyMode = "cessation";
        var newUserWriteModel = new NewUserWriteModel(
                appUser.getAverageSleepingTime(),
                appUser.getDateOfBirth(),
                appUser.getGender(),
                login,
                password,
                ageStartedSmoking,
                cigarettesPerDay,
                therapyMode
        );
        return newUserWriteModel;
    }

    public static Therapy createNewTherapySample(){
        var therapy = new Therapy();
        therapy.setPeriodBetweenCigarettes(10);
        therapy.setPlannedEndDate(LocalDate.parse("2012-02-02"));
        therapy.setStartingDate(LocalDate.parse("2012-01-01"));
        therapy.setId(10);
        return therapy;
    }

    public static Therapy createNewTherapySample(AppUser appUser){
        var therapy = new Therapy();
        therapy.setPeriodBetweenCigarettes(10);
        therapy.setPlannedEndDate(LocalDate.parse("2012-02-02"));
        therapy.setStartingDate(LocalDate.parse("2012-01-01"));
        therapy.setAppUser(appUser);
        therapy.setId(10);
        return therapy;
    }

    public static SmokingData createSmokingDataSample(){
        var smokingData = new SmokingData();
        smokingData.setAgeStartedSmoking(20);
        smokingData.setCigarettesPerDay(20);
        smokingData.setTherapyMode("cessation");
        smokingData.setId(20);
        return smokingData;
    }

    public static SmokingData createSmokingDataSample(NewUserWriteModel newUserWriteModel){
        var smokingData = new SmokingData();
        smokingData.setAgeStartedSmoking(newUserWriteModel.getAgeStartedSmoking());
        smokingData.setTherapyMode(newUserWriteModel.getTherapyMode());
        smokingData.setCigarettesPerDay(newUserWriteModel.getCigarettesPerDay());
        smokingData.setId(20);
        return smokingData;
    }
}

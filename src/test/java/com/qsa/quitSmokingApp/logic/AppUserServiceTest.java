package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppUserServiceTest {

//    @Test
//    void createNewUser() throws ParseException {
//        var newUserWriteModel = new NewUserWriteModel();
//        newUserWriteModel.setAgeStartedSmoking(20);
//        newUserWriteModel.setGender("Male");
//        newUserWriteModel.setLogin("Mark12");
//        newUserWriteModel.setDateOfBirth(DateFormat.getDateInstance().parse("2012-30-50"));
//        newUserWriteModel.setPassword("secret");
//        newUserWriteModel.setAverageSleepingTime(10);
//        newUserWriteModel.setCigarettesPerDay(15);
//        var appUserService = new AppUserService();
//
//    }
}
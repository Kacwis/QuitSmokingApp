package com.qsa.quitSmokingApp.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.UserLoginInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class NewUserWriteModel {

    private int averageSleepingTime;

    private LocalDate dateOfBirth;

    private String gender;

    private String login;

    private String password;

    private int ageStartedSmoking;

    private int cigarettesPerDay;

    private String therapyMode;

    public NewUserWriteModel(int averageSleepingTime,
                             LocalDate dateOfBirth,
                             String gender,
                             String login,
                             String password,
                             int ageStartedSmoking,
                             int cigarettesPerDay,
                             String therapyMode) {
        this.averageSleepingTime = averageSleepingTime;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.ageStartedSmoking = ageStartedSmoking;
        this.cigarettesPerDay = cigarettesPerDay;
        this.therapyMode = therapyMode;
    }

    public NewUserWriteModel() {
    }

    public int getAverageSleepingTime() {
        return averageSleepingTime;
    }

    public void setAverageSleepingTime(int averageSleepingTime) {
        this.averageSleepingTime = averageSleepingTime;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAgeStartedSmoking() {
        return ageStartedSmoking;
    }

    public void setAgeStartedSmoking(int ageStartedSmoking) {
        this.ageStartedSmoking = ageStartedSmoking;
    }

    public int getCigarettesPerDay() {
        return cigarettesPerDay;
    }

    public void setCigarettesPerDay(int cigarettesPerDay) {
        this.cigarettesPerDay = cigarettesPerDay;
    }

    public String getTherapyMode() {
        return therapyMode;
    }

    public void setTherapyMode(String therapyMode) {
        this.therapyMode = therapyMode;
    }


}

package com.qsa.quitSmokingApp.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.UserLoginInfo;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class NewUserWriteModel {

    @NotNull
    @Min(value = 2, message = "average sleeping time needs to be higher than 2")
    @Max(value = 20, message = "average sleeping time needs to be lower than 20")
    private int averageSleepingTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty(message = "gender can't be empty")
    private String gender;

    @NotNull
    @NotEmpty(message = "login can't be empty")
    private String login;

    @NotNull
    @NotEmpty(message = "password can't be empty")
    private String password;

    @NotNull
    @Min(value = 5, message = "age started smoking needs to be higher than 5")
    @Max(value = 100, message = "age started smoking needs to be lower than 100")
    private int ageStartedSmoking;

    @NotNull
    @Min(value = 5, message = "cigarettes per day needs to be higher than 5")
    @Max(value = 50, message = "cigarettes per day needs to be lower than 50")
    private int cigarettesPerDay;

    @NotNull
    @NotEmpty(message = "therapy mode can't be empty")
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

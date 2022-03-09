package com.qsa.quitSmokingApp.model.projection;

import com.qsa.quitSmokingApp.model.AppUser;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AppUserReadModel {

    private int id;

    private int averageSleepingTime;

    private LocalDate dateOfBirth;

    private String gender;

    private int ageStartedSmoking;

    private int cigarettesPerDay;

    private String therapyMode;

    public AppUserReadModel(AppUser source){
        this.id = source.getId();
        this.ageStartedSmoking = source.getSmokingInfo().getAgeStartedSmoking();
        this.dateOfBirth = source.getDateOfBirth();
        this.gender = source.getGender();
        this.cigarettesPerDay = source.getSmokingInfo().getCigarettesPerDay();
        this.therapyMode = source.getSmokingInfo().getTherapyMode();
    }

    public int getId() {
        return id;
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

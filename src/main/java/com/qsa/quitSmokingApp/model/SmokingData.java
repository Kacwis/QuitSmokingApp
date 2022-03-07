package com.qsa.quitSmokingApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "smokingInfos")
public class SmokingData {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private int cigarettesPerDay;

    private int ageStartedSmoking;

    //Reducing vs Cessation
    private String therapyMode;

    @JsonBackReference
    @OneToOne
    private AppUser appUser;


    public SmokingData(){}


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getCigarettesPerDay() {
        return cigarettesPerDay;
    }

    public void setCigarettesPerDay(int cigarettesPerDay) {
        this.cigarettesPerDay = cigarettesPerDay;
    }

    public int getAgeStartedSmoking() {
        return ageStartedSmoking;
    }

    public void setAgeStartedSmoking(int ageStartedSmoking) {
        this.ageStartedSmoking = ageStartedSmoking;
    }

    public String getTherapyMode() {
        return therapyMode;
    }

    public void setTherapyMode(String therapyMode) {
        this.therapyMode = therapyMode;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}

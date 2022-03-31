package com.qsa.quitSmokingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "appUsers")
public class AppUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private LocalDate dateOfBirth;

    private String gender;

    private int averageSleepingTime;

    @JsonManagedReference
    @OneToOne(mappedBy = "appUser")
    @JoinColumn(name = "smoking_data_id")
    private SmokingData smokingData;

    @JsonManagedReference
    @OneToOne(mappedBy = "appUser")
    @JoinColumn(name = "user_login_info_id")
    private UserLoginInfo userLoginInfo;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    private List<Therapy> therapies;

    public AppUser(){}

    public UserLoginInfo getUserLoginInfo() {
        return userLoginInfo;
    }

    public SmokingData getSmokingData() {
        return smokingData;
    }

    public void setSmokingData(SmokingData smokingData) {
        this.smokingData = smokingData;
    }

    public List<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(List<Therapy> therapies) {
        this.therapies = therapies;
    }

    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}

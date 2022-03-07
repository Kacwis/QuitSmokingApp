package com.qsa.quitSmokingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "therapies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Therapy {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private Date startingDate;

    private Date plannedEndDate;

    private double periodBetweenCigarettes;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Therapy(){}


    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public double getPeriodBetweenCigarettes() {
        return periodBetweenCigarettes;
    }

    public void setPeriodBetweenCigarettes(double periodBetweenCigarettes) {
        this.periodBetweenCigarettes = periodBetweenCigarettes;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

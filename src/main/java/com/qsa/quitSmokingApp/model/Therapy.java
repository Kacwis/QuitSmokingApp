package com.qsa.quitSmokingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.ejb.Local;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "therapies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Therapy {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private LocalDate startingDate;

    private LocalDate plannedEndDate;

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

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

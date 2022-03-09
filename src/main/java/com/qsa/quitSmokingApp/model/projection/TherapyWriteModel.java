package com.qsa.quitSmokingApp.model.projection;

import com.qsa.quitSmokingApp.model.Therapy;

import java.time.LocalDate;
import java.util.Date;

public class TherapyWriteModel {

    private float periodBetweenCigarettes;

    private LocalDate plannedEndDate;

    private LocalDate startingDate;

    private int userId;

    TherapyWriteModel(float periodBetweenCigarettes, LocalDate plannedEndDate, LocalDate startingDate, int userId){
        this.periodBetweenCigarettes = periodBetweenCigarettes;
        this.plannedEndDate = plannedEndDate;
        this.startingDate = startingDate;
        this.userId = userId;
    }

    public Therapy toTherapy(){
        var result = new Therapy();
        result.setPeriodBetweenCigarettes(periodBetweenCigarettes);
        result.setPlannedEndDate(plannedEndDate);
        result.setStartingDate(startingDate);
        return result;
    }

    public float getPeriodBetweenCigarettes() {
        return periodBetweenCigarettes;
    }

    public void setPeriodBetweenCigarettes(float periodBetweenCigarettes) {
        this.periodBetweenCigarettes = periodBetweenCigarettes;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

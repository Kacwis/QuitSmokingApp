package com.qsa.quitSmokingApp.model.projection;

import com.qsa.quitSmokingApp.model.Therapy;

import java.util.Date;

public class TherapyWriteModel {

    private float periodBetweenCigarettes;

    private Date plannedEndDate;

    private Date startingDate;

    private int userId;

    TherapyWriteModel(float periodBetweenCigarettes, Date plannedEndDate, Date startingDate, int userId){
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

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

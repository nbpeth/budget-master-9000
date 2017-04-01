package com.homebudget.domain;

import java.util.List;

public class Statistics {

    Double weekExpenses;
    List<WeekData> weeklyRollup;

    public Statistics with(Double weeklySpend){
        this.weekExpenses = weeklySpend;
        return this;
    }

    public Statistics with(List<WeekData> weeklyRollup){
        this.weeklyRollup = weeklyRollup;
        return this;
    }

    public Double getWeekExpenses() {
        return weekExpenses;
    }

    public void setWeekExpenses(Double weekExpenses) {
        this.weekExpenses = weekExpenses;
    }

    public void setWeeklyRollup(List<WeekData> weeklyRollup) {
        this.weeklyRollup = weeklyRollup;
    }

    public List<WeekData> getWeeklyRollup() {
        return weeklyRollup;
    }

}

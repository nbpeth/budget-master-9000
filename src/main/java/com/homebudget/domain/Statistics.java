package com.homebudget.domain;

import java.util.List;

public class Statistics {

    Double weekExpenses;
    List<WeekData> weeklyRollup;
    List<PieChartData> pie;

    public Statistics with(Double weeklySpend){
        this.weekExpenses = weeklySpend;
        return this;
    }

    public Statistics withPie(List<PieChartData> pie){
        this.pie = pie;
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

    public List<PieChartData> getPie() {
        return pie;
    }

    public void setPie(List<PieChartData> pie) {
        this.pie = pie;
    }

    public void setWeeklyRollup(List<WeekData> weeklyRollup) {
        this.weeklyRollup = weeklyRollup;
    }

    public List<WeekData> getWeeklyRollup() {
        return weeklyRollup;
    }

}

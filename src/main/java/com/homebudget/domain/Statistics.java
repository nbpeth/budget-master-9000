package com.homebudget.domain;

public class Statistics {

    public Double getWeeklySpend() {
        return weeklySpend;
    }

    public void setWeeklySpend(Double weeklySpend) {
        this.weeklySpend = weeklySpend;
    }

    Double weeklySpend;

    public Statistics with(Double weeklySpend){
        this.weeklySpend = weeklySpend;
        return this;
    }

}

package com.homebudget.domain;

public class Statistics {

    Double weekExpenses;

    public Statistics with(Double weeklySpend){
        this.weekExpenses = weeklySpend;
        return this;
    }

    public Double getWeekExpenses() {
        return weekExpenses;
    }

    public void setWeekExpenses(Double weekExpenses) {
        this.weekExpenses = weekExpenses;
    }

}

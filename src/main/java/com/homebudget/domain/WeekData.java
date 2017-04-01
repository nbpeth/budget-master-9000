package com.homebudget.domain;

public class WeekData {

    String weekStart;
    String weekEnd;
    Double sum;
    Integer ordinal;

    public WeekData(String weekStart, String weekEnd, Double sum){
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.sum = sum;
    }

    public WeekData id(Integer id){
        this.ordinal = id;
        return this;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }


    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString(){
        return getWeekEnd() + ", " + getWeekStart() + ", " + sum;
    }
}


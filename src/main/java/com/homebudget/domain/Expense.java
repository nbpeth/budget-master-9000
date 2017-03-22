package com.homebudget.domain;


import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    Integer id;
    Double cost;
    String location;
    String expenseType;
    String description;
    String dayOfWeek;
    Date expenseDate;

    public Expense(){

    }

    public Expense(Date expenseDate, String location, String expenseType, String description, String dayOfWeek, Double cost){
        this.cost = cost;
        this.location = location;
        this.expenseType = expenseType;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.expenseDate = expenseDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString(){
        return this.expenseDate + " " + this.getLocation() + " " + this.getCost() + " " + this.getDayOfWeek() + " " + this.getDescription() + " ";
    }
}

package com.homebudget.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
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
    Integer userId;

    @JsonFormat(pattern = "MM/dd/yyyy", timezone = "CST")
    Date expenseDate;

    public Expense(){}

    public Expense(Date expenseDate, String location, String expenseType, String description, Double cost){
        this.cost = cost;
        this.location = location;
        this.expenseType = expenseType;
        this.description = description;
        this.expenseDate = expenseDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString(){
        return this.expenseDate + " " + this.getLocation() + " " + this.getCost()  + " " + this.getDescription() + " ";
    }
}

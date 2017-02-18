package com.homebudget.controller;

import com.homebudget.domain.Expense;
import com.homebudget.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BudgetController {

    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping("/")
    public String index(){
        return "HI FRIENDS";
    }

}

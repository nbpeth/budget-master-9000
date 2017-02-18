package com.homebudget.service;

import com.homebudget.domain.Expense;
import com.homebudget.repository.ExpenseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public void deleteExpenseBy(Integer id){
        expenseRepository.delete(id);
    }

    public void deleteExpense(Expense expense){
        expenseRepository.delete(expense);
    }

    public Iterable<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public List<Expense> getExpense(String month, String day, String year){
        try{
            Date expenseDate = new SimpleDateFormat("MM/dd/yyyy").parse(month+"/"+day+"/"+year);

            return expenseRepository.findByExpenseDate(expenseDate);
        }
        catch(ParseException e){

        }

        return new ArrayList<>();

    }

}

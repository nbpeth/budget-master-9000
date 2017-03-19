package com.homebudget.service;

import com.homebudget.domain.Expense;
import com.homebudget.repository.ExpenseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Expense> getAllExpenses(Pageable pageable){
        return expenseRepository.findAllOrderByDate(pageable);
    }

    public List<Expense> getExpense(String month, String day, String year) {
        List<Expense> expenses = new ArrayList<>();

        try {
            Date expenseDate = new SimpleDateFormat("MM/dd/yyyy").parse(month + "/" + day + "/" + year);
            expenses = expenseRepository.findByExpenseDate(expenseDate);

        } catch (ParseException e) {

        }

        return expenses;

    }

    public Expense getById(Integer id){
        return expenseRepository.findOne(id);
    }

}

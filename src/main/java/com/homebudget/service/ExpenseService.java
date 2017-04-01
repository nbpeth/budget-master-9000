package com.homebudget.service;

import com.homebudget.domain.Expense;
import com.homebudget.domain.Statistics;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        Expense formattedExpense = new Expense(
                expense.getExpenseDate(),
                expense.getLocation().toUpperCase(),
                expense.getExpenseType(),
                expense.getDescription(),
                expense.getCost()
        );
        return expenseRepository.save(formattedExpense);
    }

    public void deleteExpenseBy(Integer id) {
        expenseRepository.delete(id);
    }

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAllOrderByDate(pageable);
    }

    public Expense getById(Integer id) {
        return expenseRepository.findOne(id);
    }

}

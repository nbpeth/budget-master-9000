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
import java.util.ArrayList;
import java.util.Calendar;
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

    public Statistics getStats(){
        return new Statistics()
            .with(weeklySpend());

    }

    private Double weeklySpend(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return expenseRepository.weekly(calendar.getTime(), new Date());
    }

    public Page<Expense> getAllExpenses(Pageable pageable){
        return expenseRepository.findAllOrderByDate(pageable);
    }

    public Expense getById(Integer id){
        return expenseRepository.findOne(id);
    }

}

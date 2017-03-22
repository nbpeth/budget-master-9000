package com.homebudget.service;

import com.homebudget.domain.Statistics;
import com.homebudget.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class StatisticsService {
    @Autowired
    ExpenseRepository expenseRepository;

    public Statistics getStats() {
        return new Statistics()
            .with(weekExpenses());
    }

    private Double weekExpenses() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return expenseRepository.weekly(calendar.getTime(), new Date());
    }

}

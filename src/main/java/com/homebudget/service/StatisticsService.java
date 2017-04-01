package com.homebudget.service;

import com.homebudget.domain.Statistics;
import com.homebudget.domain.WeekData;
import com.homebudget.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StatisticsService {
    @Autowired
    ExpenseRepository expenseRepository;

    public Statistics getStats() {
        return new Statistics()
                .with(weekExpenses())
                .with(weeklyRollUp());

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

    public List<WeekData> weeklyRollUp() {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int currentDay = calendar.DAY_OF_WEEK - 2;


        return IntStream.range(1, 5)
                .mapToObj(i -> {
                    Date weekEnd;
                    Date weekStart;
                    if (i == 0) {
                        calendar.add(Calendar.DAY_OF_WEEK, -currentDay);
                        weekEnd = calendar.getTime();
                    } else {
                        weekEnd = calendar.getTime();
                    }
                    calendar.add(Calendar.DAY_OF_WEEK, -7);
                    weekStart = calendar.getTime();

                    return new WeekData(
                            new SimpleDateFormat("MM-dd-yyyy").format(weekStart),
                            new SimpleDateFormat("MM-dd-yyyy").format(weekEnd),
                            expenseRepository.weekly(weekStart, weekEnd)
                    ).id(i);
                })
                .collect(Collectors.toList());
    }

}

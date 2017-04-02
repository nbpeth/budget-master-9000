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
        Calendar calendar = (Calendar) Calendar.getInstance().clone();

        calendar.add(Calendar.DAY_OF_WEEK, - (Calendar.DAY_OF_WEEK - 1));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return expenseRepository.weekly(calendar.getTime(), new Date());
    }

    private List<WeekData> weeklyRollUp() {
        final String dateFormat = "MM-dd-yyyy";
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return IntStream.range(0, 4)
            .mapToObj(i -> {
                Date weekEnd;
                Date weekStart;
                if (i == 0) {
                    calendar.add(Calendar.DAY_OF_WEEK, - (calendar.DAY_OF_WEEK > 1 ? calendar.DAY_OF_WEEK - 1 : 0));
                    calendar.add(Calendar.MILLISECOND, -1);
                    weekEnd = calendar.getTime();
                    calendar.add(Calendar.MILLISECOND, 1);

                } else {
                    calendar.add(Calendar.MILLISECOND, -1);
                    weekEnd = calendar.getTime();
                    calendar.add(Calendar.MILLISECOND, 1);
                }

                calendar.add(Calendar.DAY_OF_WEEK, -7);
                weekStart = calendar.getTime();

                return new WeekData(
                        new SimpleDateFormat(dateFormat).format(weekStart),
                        new SimpleDateFormat(dateFormat).format(weekEnd),
                        expenseRepository.weekly(weekStart, weekEnd)
                ).id(i);
            })
            .collect(Collectors.toList());
    }

}

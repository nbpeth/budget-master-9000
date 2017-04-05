package com.homebudget.service;

import com.homebudget.domain.Statistics;
import com.homebudget.domain.WeekData;
import com.homebudget.repository.ExpenseRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
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
        return expenseRepository.weekly(new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDate(), new Date());
    }

    private List<WeekData> weeklyRollUp() {
        final String dateFormat = "MM-dd-yyyy:hh:mm:ss";

        return IntStream.range(0, 4)
            .mapToObj(i -> {
                DateTime dateTime = new DateTime();
                Date weekEnd = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.SUNDAY).plusDays(1).minusDays(7 * i)).minusMillis(1).toDate();
                Date weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY).minusDays(7 * i)).toDate();

                if (i == 0) {
                    int currentDay = dateTime.getDayOfWeek();
                    weekEnd = truncateDate(dateTime.minusDays(currentDay > 1 ? currentDay - 1 : 0)).minusMillis(1).toDate();
                    weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY)).toDate();
                }

                return new WeekData(
                        new SimpleDateFormat(dateFormat).format(weekStart),
                        new SimpleDateFormat(dateFormat).format(weekEnd),
                        expenseRepository.weekly(weekStart, weekEnd)
                ).id(i);
            })
            .collect(Collectors.toList());
    }

    private static DateTime truncateDate(DateTime dateTime){
        return dateTime
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
    }

}

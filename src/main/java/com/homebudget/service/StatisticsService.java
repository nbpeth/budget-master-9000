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
import java.util.Date;
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
        java.sql.Date sqlDate = new java.sql.Date(new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDate().getTime());
        return expenseRepository.weekly(sqlDate, new java.sql.Date(new Date().getTime()));
    }

    private List<WeekData> weeklyRollUp() {
        final String dateFormat = "MM-dd-yyyy";

        return IntStream.range(0, 4)
            .mapToObj(i -> {
                DateTime dateTime = new DateTime();
                Date weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY).minusDays(7 * i)).toDate();
                Date weekEnd = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.SUNDAY).plusDays(1).minusDays(7 * i)).minusMillis(1).toDate();

                if (i == 0) {
                    weekEnd = new Date();
                    weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY)).toDate();
                }

                return new WeekData(
                        new SimpleDateFormat(dateFormat).format(weekStart),
                        new SimpleDateFormat(dateFormat).format(weekEnd),
                        expenseRepository.weekly(new java.sql.Date(weekStart.getTime()), new java.sql.Date(weekEnd.getTime()))
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

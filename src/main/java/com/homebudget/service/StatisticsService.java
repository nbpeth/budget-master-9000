package com.homebudget.service;

import com.homebudget.domain.PieChartData;
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
    private static final String dateFormat = "MM-dd-yyyy";


    public WeekData getStatsForWeek(String username, Integer week){
        return getDataForWeek(username, week);
    }

    private WeekData getDataForWeek(String username, Integer i){
        DateTime dateTime = new DateTime();
        Date weekStart;
        Date weekEnd;

        weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY)
                .minusDays(7 * i))
                .toDate();
        weekEnd = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.SUNDAY)
                .plusDays(1)
                .minusDays(7 * i))
                .minusMillis(1)
                .toDate();

        return new WeekData(
                new SimpleDateFormat(dateFormat).format(weekStart),
                new SimpleDateFormat(dateFormat).format(weekEnd),
                expenseRepository.weekly(new java.sql.Date(weekStart.getTime()), new java.sql.Date(weekEnd.getTime()), username));
    }

    public Statistics getStats(String username) {
        return new Statistics()
            .with(weekExpenses(username))
            .with(weeklyRollUp(username))
            .withPie(weeklyPie(username));
    }

    private Double weekExpenses(String username) {
        java.sql.Date sqlDate = new java.sql.Date(new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDate().getTime());
        return expenseRepository.weekly(sqlDate, new java.sql.Date(new Date().getTime()), username);
    }

    private List<PieChartData> weeklyPie(String username){
        java.sql.Date sqlDate = new java.sql.Date(new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDate().getTime());
        List<Object> prePie = expenseRepository.weeklyPie(sqlDate, new java.sql.Date(new Date().getTime()), username);

        List<PieChartData> data = prePie.stream().map(group -> {
            Object[] arr = (Object[]) group;
            Object key = arr[1];
            Double value = (Double) arr[0] * 100;
            Integer formattedValue = (value.intValue());

            PieChartData pieData = new PieChartData();
            pieData.setLabel((String) key);
            pieData.setValue(formattedValue);
            return pieData;
        })
        .collect(Collectors.toList());

        return IntStream.range(0,data.size())
                .mapToObj(i-> {
                    PieChartData pieChartData = data.get(i);
                    return pieChartData;
                })
                .collect(Collectors.toList());

    }

    private List<WeekData> weeklyRollUp(String username) {
        return IntStream.range(0, 8)
            .mapToObj(i -> getDataForWeek(username, i)
//                DateTime dateTime = new DateTime();
//                Date weekStart;
//                Date weekEnd;
//
//                weekStart = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.MONDAY)
//                        .minusDays(7 * i))
//                        .toDate();
//                weekEnd = truncateDate(dateTime.withDayOfWeek(DateTimeConstants.SUNDAY)
//                        .plusDays(1)
//                        .minusDays(7 * i))
//                        .minusMillis(1)
//                        .toDate();
//
//                return new WeekData(
//                        new SimpleDateFormat(dateFormat).format(weekStart),
//                        new SimpleDateFormat(dateFormat).format(weekEnd),
//                        expenseRepository.weekly(new java.sql.Date(weekStart.getTime()), new java.sql.Date(weekEnd.getTime()), username)
            .id(i))
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

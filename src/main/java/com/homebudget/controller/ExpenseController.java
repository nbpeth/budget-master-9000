package com.homebudget.controller;

import com.homebudget.domain.Expense;
import com.homebudget.domain.Statistics;
import com.homebudget.domain.WeekData;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.ExpenseService;
import com.homebudget.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExpenseController extends BaseController {
    @Autowired ExpenseService expenseService;
    @Autowired StatisticsService statisticsService;


    @PostMapping("/expenses/{requestedUsername:.+}")
    public ResponseEntity<Expense> submitExpense(HttpServletRequest servletRequest, @RequestBody Expense expense, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        expense.setUsername(username);

        expenseService.saveExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @GetMapping("/expenses/{requestedUsername:.+}/stats")
    public ResponseEntity<Statistics> getStats(HttpServletRequest servletRequest, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        Statistics statistics = statisticsService.getStats(username);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @GetMapping("/expenses/{requestedUsername:.+}/stats/week/{week}")
    public ResponseEntity<WeekData> getStatsForAWeek(HttpServletRequest servletRequest, @PathVariable String requestedUsername, @PathVariable Integer week) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        WeekData weekData = statisticsService.getStatsForWeek(requestedUsername, week);

        return new ResponseEntity<>(weekData, HttpStatus.OK);
    }

    @GetMapping("/expenses/{requestedUsername:.+}")
    public ResponseEntity<Page<Expense>> getAllExpenses(HttpServletRequest servletRequest, Pageable pageable, @PathVariable String requestedUsername) throws UnauthorizedException {

        String username = getAndValidateUser(servletRequest, requestedUsername);

        return new ResponseEntity<>(expenseService.getAllExpenses(pageable, username), HttpStatus.OK);
    }

    @GetMapping("/expenses/{requestedUsername:.+}/{id}")
    public ResponseEntity<Expense> getById(HttpServletRequest servletRequest, @PathVariable Integer id, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        return new ResponseEntity<>(expenseService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{requestedUsername:.+}/{id}")
    public ResponseEntity<Expense> deleteById(HttpServletRequest servletRequest, @PathVariable Integer id, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        Expense expense = expenseService.deleteExpenseBy(id);

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

}

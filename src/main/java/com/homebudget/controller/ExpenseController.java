package com.homebudget.controller;

import com.homebudget.controller.authentication.BaseController;
import com.homebudget.domain.Expense;
import com.homebudget.domain.Statistics;
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


    @PostMapping("/expenses")
    public ResponseEntity<Expense> submitExpense(HttpServletRequest servletRequest, @RequestBody Expense expense) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);

        expense.setUsername(username);

        expenseService.saveExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @GetMapping("/expenses/stats")
    public ResponseEntity<Statistics> getStats(HttpServletRequest servletRequest) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);
        Statistics statistics = statisticsService.getStats(username);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<Page<Expense>> getAllExpenses(HttpServletRequest servletRequest, Pageable pageable) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);

        return new ResponseEntity<>(expenseService.getAllExpenses(pageable, username), HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getById(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);

        return new ResponseEntity<>(expenseService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteById(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);

        expenseService.deleteExpenseBy(id);
    }

}

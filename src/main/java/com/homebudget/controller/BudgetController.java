package com.homebudget.controller;

import com.homebudget.domain.Expense;
import com.homebudget.domain.Statistics;
import com.homebudget.repository.ExpenseRepository;
import com.homebudget.service.ExpenseService;
import com.homebudget.service.StatisticsService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
public class BudgetController {
    @Autowired
    ExpenseService expenseService;
    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/expenses")
    public ResponseEntity<Expense> submitExpense(@RequestBody Expense expense) throws BadHttpRequest {
        expenseService.saveExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @GetMapping("/expenses/stats")
    public ResponseEntity<Statistics> getStats(HttpServletRequest servletRequest){
        return new ResponseEntity<>(statisticsService.getStats(), HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<Page<Expense>> getAllExpenses(Pageable pageable){
        return new ResponseEntity<>(expenseService.getAllExpenses(pageable), HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Integer id){
        return new ResponseEntity<>(expenseService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteById(@PathVariable Integer id){
        expenseService.deleteExpenseBy(id);
    }

}

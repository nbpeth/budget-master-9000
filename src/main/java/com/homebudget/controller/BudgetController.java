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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://10.0.1.9", maxAge = 3600)
public class BudgetController {
    @Autowired
    ExpenseService expenseService;
    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/expenses")
    public ResponseEntity<Expense> submitExpense(@RequestBody Expense expense) throws BadHttpRequest {
        expenseService.saveExpense(expense);
        HttpHeaders headers = new HttpHeaders();
        List<String> origins = new ArrayList<>();
        origins.add("http://10.0.1.9");
        headers.put("Access-Control-Allow-Origin", origins);
        return new ResponseEntity<>(expense, headers, HttpStatus.CREATED);
    }

    @GetMapping("/expenses/stats")
    public ResponseEntity<Statistics> getStats(HttpServletRequest servletRequest){
        HttpHeaders headers = new HttpHeaders();
        List<String> origins = new ArrayList<>();
        origins.add("http://10.0.1.9");
        headers.put("Access-Control-Allow-Origin", origins);
        return new ResponseEntity<>(statisticsService.getStats(), headers, HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<Page<Expense>> getAllExpenses(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        List<String> origins = new ArrayList<>();
        origins.add("http://10.0.1.9");
        headers.put("Access-Control-Allow-Origin", origins);
        return new ResponseEntity<>(expenseService.getAllExpenses(pageable), headers, HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        List<String> origins = new ArrayList<>();
        origins.add("http://10.0.1.9");
        headers.put("Access-Control-Allow-Origin", origins);
        return new ResponseEntity<>(expenseService.getById(id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteById(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        List<String> origins = new ArrayList<>();
        origins.add("http://10.0.1.9");
        headers.put("Access-Control-Allow-Origin", origins);
        expenseService.deleteExpenseBy(id);
    }

}

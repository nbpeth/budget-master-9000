package com.homebudget.controller;

import com.homebudget.controller.authentication.BaseController;
import com.homebudget.domain.RecurringExpense;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class RecurringExpenseController extends BaseController {

    @Autowired RecurringExpenseService recurringExpenseService;

    @GetMapping("/expenses/recurring")
    public ResponseEntity<Iterable<RecurringExpense>> getRecurringExpenses(HttpServletRequest servletRequest) throws UnauthorizedException {
        Map<String, String> claims = validateToken(servletRequest);
        Iterable<RecurringExpense> recurringExpenses = recurringExpenseService.getRecurringExpenses();

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> getRecurringExpenseById(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        Map<String, String> claims = validateToken(servletRequest);
        RecurringExpense recurringExpenses = recurringExpenseService.getRecurringExpenseById(id);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @PostMapping("/expenses/recurring")
    public ResponseEntity<RecurringExpense> createRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense) throws UnauthorizedException {
        Map<String, String> claims = validateToken(servletRequest);
        RecurringExpense newRecurringExpense = recurringExpenseService.createRecurringExpense(recurringExpense);

        return new ResponseEntity<>(newRecurringExpense, HttpStatus.OK);
    }

    @PatchMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> updateRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense, @PathVariable Integer id) throws UnauthorizedException {
        Map<String, String> claims = validateToken(servletRequest);
        recurringExpense.setId(id);
        //this is a sub optimal, hamfisted update, fix it
        RecurringExpense updatedRecurringExpense = recurringExpenseService.updateRecurringExpense(recurringExpense);

        return new ResponseEntity<>(updatedRecurringExpense, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/recurring/{id}")
    public ResponseEntity<String> deleteRecurringExpense(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        Map<String, String> claims = validateToken(servletRequest);
        recurringExpenseService.deleteRecurringExpense(id);

        return new ResponseEntity<>("Great job.", HttpStatus.OK);
    }

}

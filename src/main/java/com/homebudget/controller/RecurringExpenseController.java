package com.homebudget.controller;

import com.homebudget.domain.RecurringExpense;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecurringExpenseController extends BaseController {

    @Autowired RecurringExpenseService recurringExpenseService;

    @GetMapping("/expenses/{requestedUsername:.+}/recurring")
    public ResponseEntity<Iterable<RecurringExpense>> getRecurringExpenses(HttpServletRequest servletRequest, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        Iterable<RecurringExpense> recurringExpenses = recurringExpenseService.getRecurringExpenses(username);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/{requestedUsername:.+}/recurring/{id}")
    public ResponseEntity<RecurringExpense> getRecurringExpenseById(HttpServletRequest servletRequest, @PathVariable Integer id, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);
        RecurringExpense recurringExpenses = recurringExpenseService.getRecurringExpenseById(id);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @PostMapping("/expenses/{requestedUsername:.+:.+}/recurring")
    public ResponseEntity<RecurringExpense> createRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);
        recurringExpense.setUsername(username);

        RecurringExpense newRecurringExpense = recurringExpenseService.createRecurringExpense(recurringExpense);

        return new ResponseEntity<>(newRecurringExpense, HttpStatus.OK);
    }

    @PatchMapping("/expenses/{requestedUsername:.+}/recurring/{id}")
    public ResponseEntity<RecurringExpense> updateRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense, @PathVariable Integer id, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);
        recurringExpense.setId(id);

        RecurringExpense updatedRecurringExpense = recurringExpenseService.updateRecurringExpense(recurringExpense);

        return new ResponseEntity<>(updatedRecurringExpense, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{requestedUsername:.+}/recurring/{id}")
    public ResponseEntity<String> deleteRecurringExpense(HttpServletRequest servletRequest, @PathVariable Integer id, @PathVariable String requestedUsername) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest, requestedUsername);

        recurringExpenseService.deleteRecurringExpense(id);

        return new ResponseEntity<>("Great job.", HttpStatus.OK);
    }

}

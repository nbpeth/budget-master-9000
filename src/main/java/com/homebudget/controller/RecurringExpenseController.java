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

@RestController
public class RecurringExpenseController extends BaseController {

    @Autowired RecurringExpenseService recurringExpenseService;

    @GetMapping("/expenses/recurring")
    public ResponseEntity<Iterable<RecurringExpense>> getRecurringExpenses(HttpServletRequest servletRequest) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);

        Iterable<RecurringExpense> recurringExpenses = recurringExpenseService.getRecurringExpenses(username);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> getRecurringExpenseById(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);
        RecurringExpense recurringExpenses = recurringExpenseService.getRecurringExpenseById(id);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @PostMapping("/expenses/recurring")
    public ResponseEntity<RecurringExpense> createRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);
        recurringExpense.setUsername(username);

        RecurringExpense newRecurringExpense = recurringExpenseService.createRecurringExpense(recurringExpense);

        return new ResponseEntity<>(newRecurringExpense, HttpStatus.OK);
    }

    @PatchMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> updateRecurringExpense(HttpServletRequest servletRequest, @RequestBody RecurringExpense recurringExpense, @PathVariable Integer id) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);
        recurringExpense.setId(id);
        //validate token user matches record user?
        RecurringExpense updatedRecurringExpense = recurringExpenseService.updateRecurringExpense(recurringExpense);

        return new ResponseEntity<>(updatedRecurringExpense, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/recurring/{id}")
    public ResponseEntity<String> deleteRecurringExpense(HttpServletRequest servletRequest, @PathVariable Integer id) throws UnauthorizedException {
        String username = getAndValidateUser(servletRequest);
        //validate token user matches record user?
        recurringExpenseService.deleteRecurringExpense(id);

        return new ResponseEntity<>("Great job.", HttpStatus.OK);
    }

}

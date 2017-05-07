package com.homebudget.controller;

import com.homebudget.domain.RecurringExpense;
import com.homebudget.service.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecurringExpenseController {

    @Autowired
    RecurringExpenseService recurringExpenseService;

    @GetMapping("/expenses/recurring")
    public ResponseEntity<Iterable<RecurringExpense>> getRecurringExpenses(){
        Iterable<RecurringExpense> recurringExpenses = recurringExpenseService.getRecurringExpenses();

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> getRecurringExpenseById(@PathVariable Integer id){
        RecurringExpense recurringExpenses = recurringExpenseService.getRecurringExpenseById(id);

        return new ResponseEntity<>(recurringExpenses, HttpStatus.OK);
    }

    @PostMapping("/expenses/recurring")
    public ResponseEntity<RecurringExpense> createRecurringExpense(@RequestBody RecurringExpense recurringExpense){
        RecurringExpense newRecurringExpense = recurringExpenseService.createRecurringExpense(recurringExpense);

        return new ResponseEntity<>(newRecurringExpense, HttpStatus.OK);
    }

    @PatchMapping("/expenses/recurring/{id}")
    public ResponseEntity<RecurringExpense> updateRecurringExpense(@RequestBody RecurringExpense recurringExpense){
        RecurringExpense updatedRecurringExpense = recurringExpenseService.updateRecurringExpense(recurringExpense);

        return new ResponseEntity<>(updatedRecurringExpense, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/recurring/{id}")
    public ResponseEntity<String> deleteRecurringExpense(@PathVariable Integer id){
        recurringExpenseService.deleteRecurringExpense(id);

        return new ResponseEntity<>("Great job.", HttpStatus.OK);
    }

}

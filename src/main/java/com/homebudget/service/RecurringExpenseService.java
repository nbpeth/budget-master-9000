package com.homebudget.service;

import com.homebudget.domain.RecurringExpense;
import com.homebudget.repository.RecurringExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RecurringExpenseService {

    @Autowired
    private RecurringExpenseRepository recurringExpenseRepository;

    public Iterable<RecurringExpense> getRecurringExpenses(){
        return recurringExpenseRepository.findAllSortDesc();
    }

    public RecurringExpense getRecurringExpenseById(Integer id){
        return recurringExpenseRepository.findOne(id);
    }

    public RecurringExpense createRecurringExpense(RecurringExpense newExpense){
        return recurringExpenseRepository.save(newExpense);
    }

    public RecurringExpense updateRecurringExpense(RecurringExpense newExpense){
        RecurringExpense recurringExpense = recurringExpenseRepository.findOne(newExpense.getId());
        recurringExpense.setCost(newExpense.getCost());
        recurringExpense.setSpan(newExpense.getSpan());
        recurringExpense.setName(newExpense.getName());
        recurringExpense.setDescription(newExpense.getDescription());

        return recurringExpenseRepository.save(recurringExpense);
    }

    public void deleteRecurringExpense(Integer id){
        try {
            recurringExpenseRepository.delete(id);
        }
        catch(Exception e){
            System.out.println("Unable to delete because " + e.getMessage());
        }
    }

}

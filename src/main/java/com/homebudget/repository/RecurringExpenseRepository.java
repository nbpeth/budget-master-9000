package com.homebudget.repository;

import com.homebudget.domain.RecurringExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringExpenseRepository extends CrudRepository<RecurringExpense, Integer> {

}

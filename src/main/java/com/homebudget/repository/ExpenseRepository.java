package com.homebudget.repository;


import com.homebudget.domain.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    List<Expense> findByExpenseDate(Date date);

}

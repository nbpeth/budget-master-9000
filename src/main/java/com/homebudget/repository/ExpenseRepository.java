package com.homebudget.repository;


import com.homebudget.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {

    List<Expense> findByExpenseDate(Date date);

    Page<Expense> findAllOrderByDate(Pageable pageable);

}

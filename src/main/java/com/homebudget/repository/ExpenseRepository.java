package com.homebudget.repository;

import com.homebudget.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {
    @Query("SELECT SUM(e.cost) FROM Expense e WHERE e.expenseDate >= (:startDate) and e.expenseDate <= (:endDate)")
    Double weekly(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    @Query("SELECT expense FROM Expense expense order by expenseDate desc")
    Page<Expense> findAllOrderByDate(Pageable pageable);

//    @Query("SELECT sum(expense.cost) as cost, DATE_ADD(expense.expenseDate, INTERVAL(2-DAYOFWEEK(expense.expenseDate)) DAY), DATE_ADD(expense.expenseDate, INTERVAL(8-DAYOFWEEK(expense.expenseDate)) DAY) as week_end from Expense expense")


}

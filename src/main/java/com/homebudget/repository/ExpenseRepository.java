package com.homebudget.repository;


import com.homebudget.domain.Expense;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {
    @Query("SELECT SUM(e.cost) FROM Expense e WHERE e.expenseDate between (:startDate) and (:endDate)")
    Double weekly(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    @Query("SELECT e FROM Expense e order by expenseDate desc")
    Page<Expense> findAllOrderByDate(Pageable pageable);

    List<Expense> findByExpenseDate(Date date);


}

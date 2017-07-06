package com.homebudget.repository;

import com.homebudget.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {
    @Query("SELECT SUM(e.cost) FROM Expense e WHERE e.expenseDate >= (:startDate) and e.expenseDate <= (:endDate) and e.username = (:username)")
    Double weekly(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("username")String username);

    @Query("SELECT SUM(e.cost)/300, e.expenseType FROM Expense e WHERE e.expenseDate >= (:startDate) and e.expenseDate <= (:endDate) and e.username = (:username) group by expenseType")
    List<Object> weeklyPie(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("username")String username);

    @Query("SELECT expense FROM Expense expense where expense.username = (:username) order by expenseDate desc")
    Page<Expense> findAllOrderByDate(Pageable pageable, @Param("username")String username);

}

package com.homebudget.repository;

import com.homebudget.domain.RecurringExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RecurringExpenseRepository extends CrudRepository<RecurringExpense, Integer> {
    @Query("SELECT r from RecurringExpense r where username = (:username) order by r.id desc")
    public List<RecurringExpense> findAllSortDesc(@Param("username")String username);

}

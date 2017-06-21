package com.homebudget.repository.authentication;

import com.homebudget.domain.RecurringExpense;
import com.homebudget.domain.authentication.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u where username = (:username)")
    User findUserByName(@Param("username")String username);

}

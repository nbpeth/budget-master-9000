package com.homebudget.controller;

import com.homebudget.service.ExpenseService;
import org.junit.Before;
import org.junit.Test;

public class ExpenseServiceTest {

    @Test
    public void doDateStuff(){

        new ExpenseService().dates();

    }

}

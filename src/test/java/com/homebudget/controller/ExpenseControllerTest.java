package com.homebudget.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExpenseControllerTest {

    ExpenseController expenseController;

    @Before
    public void setup(){
        expenseController = new ExpenseController();
    }

    @Test
    public void indexReturnsHelloWorld(){

        assertEquals("Hello world", expenseController.root("Hello world"));
    }

    @Test
    public void indexReturnsSomethingElse(){

        assertEquals("Burger Palace", expenseController.root("Burger Palace"));
    }

}

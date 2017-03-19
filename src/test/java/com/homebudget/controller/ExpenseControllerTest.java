package com.homebudget.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExpenseControllerTest {

    ViewController expenseController;

    @Before
    public void setup(){
        expenseController = new ViewController();
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

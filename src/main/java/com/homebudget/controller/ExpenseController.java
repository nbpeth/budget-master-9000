package com.homebudget.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping("/plants/{plant}")
    public String root(@PathVariable String plant){


        return "hey michelle, I love you! and I love " + plant + " !!!!";
    }

}

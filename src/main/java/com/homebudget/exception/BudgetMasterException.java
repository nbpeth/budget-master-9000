package com.homebudget.exception;

import org.springframework.http.HttpStatus;

public class BudgetMasterException extends Exception {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    BudgetMasterException(){
        super();

    }

    BudgetMasterException(String message){
        super(message);

    }

}

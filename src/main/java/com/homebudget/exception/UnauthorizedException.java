package com.homebudget.exception;


import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BudgetMasterException {

    public UnauthorizedException(){
        super();
        this.setStatus(httpStatus());
    }

    public UnauthorizedException(String message){
        super(message);
        this.setStatus(httpStatus());

    }

    private HttpStatus httpStatus(){
        return HttpStatus.UNAUTHORIZED;

    }
}

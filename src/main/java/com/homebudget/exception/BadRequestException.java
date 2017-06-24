package com.homebudget.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BudgetMasterException {
    public BadRequestException(){
        super();
        this.setStatus(httpStatus());
    }

    public BadRequestException(String message){
        super(message);
        this.setStatus(httpStatus());

    }

    private HttpStatus httpStatus(){
        return HttpStatus.BAD_REQUEST;

    }
}

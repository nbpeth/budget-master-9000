package com.homebudget.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BudgetMasterException {
    public NotFoundException(){
        super();
        this.setStatus(httpStatus());
    }

    public NotFoundException(String message){
        super(message);
        this.setStatus(httpStatus());

    }

    private HttpStatus httpStatus(){
        return HttpStatus.NOT_FOUND;

    }
}

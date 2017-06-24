package com.homebudget.controller.authentication;

import com.homebudget.exception.BudgetMasterException;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {
    @Autowired TokenService tokenService;

    protected void validateToken(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        String token = httpServletRequest.getHeader("Authorization");

        if(!tokenService.validateToken(token)){
            throw new UnauthorizedException();
        }
    }

    @ExceptionHandler(BudgetMasterException.class)
    public ResponseEntity<String> authenticationError(BudgetMasterException e) {

        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

}

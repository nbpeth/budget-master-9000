package com.homebudget.controller;

import com.homebudget.exception.BudgetMasterException;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class BaseController {
    @Autowired private TokenService tokenService;

    String getAndValidateUser(HttpServletRequest httpServletRequest, String requestedUser) throws UnauthorizedException {
        Map<String, String> claims = validateToken(httpServletRequest);
        String username = claims.get("username");

        if(!requestedUser.equals(username)) throw new UnauthorizedException("You do not have permission to access this resource.");

        return username;
    }

    Map<String, String> validateToken(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        String token = httpServletRequest.getHeader("Authorization");

        try{
            return tokenService.validateToken(token);
        }
        catch(Exception e){
            throw new UnauthorizedException();

        }
    }

    @ExceptionHandler(BudgetMasterException.class)
    public ResponseEntity<String> authenticationError(BudgetMasterException e) {

        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

}

package com.homebudget.controller.authentication;

import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Void> authenticationError() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}

package com.homebudget.controller.authentication;

import com.homebudget.domain.authentication.User;
import com.homebudget.exception.BadRequestException;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.authentication.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController extends BaseController{
    @Autowired private LoginService loginService;
    @Autowired private Map<String, String> environmentVariables;

    @PostMapping("/auth")
    public ResponseEntity<Map> authenticate(@RequestBody User user) throws UnauthorizedException {

        return loginService.authenticate(user);
    }

    @PostMapping("/auth/create")
    public ResponseEntity<Void> createUser(HttpServletRequest httpServletRequest, @RequestBody User user) throws BadRequestException {
        String token = httpServletRequest.getHeader("Authorization");

        if(token == null || !token.equals(environmentVariables.get("apiKey"))){
            throw new BadRequestException("You do not have authorization to perform this action");
        }

        return loginService.create(user);
    }
}

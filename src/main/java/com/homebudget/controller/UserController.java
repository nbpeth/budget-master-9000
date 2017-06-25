package com.homebudget.controller;

import com.homebudget.controller.authentication.BaseController;
import com.homebudget.domain.authentication.User;
import com.homebudget.exception.NotFoundException;
import com.homebudget.exception.UnauthorizedException;
import com.homebudget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(HttpServletRequest httpServletRequest, @PathVariable String username) throws UnauthorizedException, NotFoundException {
        validateToken(httpServletRequest);

        User user = userService.getUserBy(username);
        user.setPassword("");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}

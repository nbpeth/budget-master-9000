package com.homebudget.service;

import com.homebudget.domain.authentication.User;
import com.homebudget.exception.NotFoundException;
import com.homebudget.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserBy(String username) throws NotFoundException {
        User user = userRepository.findUserByName(username);
        if(user == null){
            throw new NotFoundException();
        }
        return user;
    }

}

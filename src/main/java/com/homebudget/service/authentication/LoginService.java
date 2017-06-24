package com.homebudget.service.authentication;

import com.homebudget.domain.authentication.User;
import com.homebudget.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginService {
    @Autowired private UserRepository userRepository;
    @Autowired private TokenService tokenService;

    public ResponseEntity<Map> authenticate(User user) {
        User userToAuthenticate = userRepository.findUserByName(user.getUsername());

        String password = user.getPassword();
        String realPassword = userToAuthenticate.getPassword();

        boolean correctCredentials = validatePassword(password, realPassword);

        if(!correctCredentials){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = tokenService.generateToken(user);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);

        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }

    public ResponseEntity<Void> create(User user) {
        String rawPassword = user.getPassword();
        String hashedPassword = hashPassword(rawPassword);

        user.setPassword(hashedPassword);
        user.setJoinDate(new Date());
        user.setActive(true);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    private boolean validatePassword(String rawPassword, String hashedPassword){
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }
}
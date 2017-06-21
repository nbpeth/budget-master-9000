package com.homebudget.service.authentication;

import com.homebudget.domain.authentication.User;
import com.homebudget.repository.authentication.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class LoginService {
    @Autowired private UserRepository userRepository;
    @Autowired private String apiSecret;

    public ResponseEntity<String> authenticate(User user) {
        User userToAuthenticate = userRepository.findUserByName(user.getUsername());

        String password = user.getPassword();
        String realPassword = userToAuthenticate.getPassword();

        boolean correctCredentials = validatePassword(password, realPassword);

        if(!correctCredentials){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = generateToken();

        return new ResponseEntity<>(token, HttpStatus.OK);
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

    private String generateToken(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("id")
            .setIssuedAt(now)
            .setSubject("auth")
            .setIssuer("budgetMaster")
            .setExpiration(new Date(now.getTime() + 2592000000L))
            .signWith(signatureAlgorithm, signingKey);

        return jwtBuilder.compact();
    }

    public boolean validateToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiSecret))
                .parseClaimsJws(token).getBody();

        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());

        return true;
    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    private boolean validatePassword(String rawPassword, String hashedPassword){
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }
}

//    function parseJwt (token) {
//        var base64Url = token.split('.')[1];
//        var base64 = base64Url.replace('-', '+').replace('_', '/');
//        return JSON.parse(window.atob(base64));
//    };
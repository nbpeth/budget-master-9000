package com.homebudget.service.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.homebudget.domain.authentication.User;
import com.homebudget.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenService {
    @Autowired
    private Map<String, String> environmentVariables;

    public String generateToken(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(environmentVariables.get("apiSecret"));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(now)
                .setHeader(jwtHeaders())
                .setClaims(claimsFor(user))
                .setExpiration(new Date(now.getTime() + 2592000000L))
                .signWith(signatureAlgorithm, signingKey);

        return jwtBuilder.compact();
    }

    private Map<String, Object> claimsFor(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        return claims;
    }

    private Map<String, Object> jwtHeaders(){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "JWT");

        return headers;
    }

//    public boolean validateToken(String token) throws UnauthorizedException {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(DatatypeConverter.parseBase64Binary(environmentVariables.get("apiSecret")))
//                    .parseClaimsJws(token).getBody();
//
//        } catch (Exception e) {
//            throw new UnauthorizedException();
//        }
//
//        return true;
//    }

    public boolean validateToken(String token) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("559785174037-t1mifh0tkkebo55e4v26fhiqtb70a84k.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(token);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            System.out.println("VALIDATED");
            System.out.println("email");

            return true;
        } else {
            System.out.println("Invalid ID token.");
            return false;
        }
    }

}

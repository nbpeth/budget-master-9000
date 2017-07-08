package com.homebudget.service.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.homebudget.domain.authentication.User;
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

    public Map<String, String> validateToken(String token) throws GeneralSecurityException, IOException, NullPointerException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("559785174037-t1mifh0tkkebo55e4v26fhiqtb70a84k.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(token);
        GoogleIdToken.Payload payload = idToken.getPayload();

        String userId = payload.getSubject();
        String email = payload.getEmail();

        Map<String, String> claims = new HashMap<>();
        claims.put("username",email);
        claims.put("userId",userId);

        return claims;

    }
    //            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
    //            String name = (String) payload.get("name");
    //            String pictureUrl = (String) payload.get("picture");
    //            String locale = (String) payload.get("locale");
    //            String familyName = (String) payload.get("family_name");
    //            String givenName = (String) payload.get("given_name");

}

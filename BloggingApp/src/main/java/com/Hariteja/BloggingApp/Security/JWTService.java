package com.Hariteja.BloggingApp.Security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String JWT_Key= "gldmsfalsmdaklndlaknfafaslmnffasnklfneknfwaf";
    private Algorithm algorithm=  Algorithm.HMAC256(JWT_Key);

    public String createJWT(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+ 60*60*30))
                .sign(algorithm);

    }

    public Long retrieveUserId(String jwtString) {
        var decodedJWT = JWT.decode(jwtString);
        return Long.valueOf(decodedJWT.getSubject());
    }
}

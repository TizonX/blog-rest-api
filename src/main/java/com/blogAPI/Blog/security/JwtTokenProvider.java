package com.blogAPI.Blog.security;

import com.blogAPI.Blog.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;

    // generate JWT Token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        // set expiration date
        // so, lets first add the current date

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token =  Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate).signWith(key()).compact();

        return token;
    }

    private Key key()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get user name from jwt token
    public String getUsername(String token)
    {
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        return username;
    }



    // validate jwt token
    public boolean validateToken(String token){
    try{
        Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
        return  true;

    }
    catch (MalformedJwtException ex){
        throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt token");
    }
    catch (ExpiredJwtException ex){
        throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired Jwt token");
    }
    catch (UnsupportedJwtException ex){
        throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported Jwt token");
    }
    catch (IllegalArgumentException ex){
        throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT Claims String is empty");
    }

    }
}

package com.blogAPI.Blog.controller;

import com.blogAPI.Blog.payload.JwtAuthResponse;
import com.blogAPI.Blog.payload.LogginDto;
import com.blogAPI.Blog.payload.RegisterDto;
import com.blogAPI.Blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //
    @PostMapping(value = {"/login","/signin "})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LogginDto logginDto){
        String token = authService.login(logginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
    // sign-up
    @PostMapping(value = {"/register","/signup "})
    public ResponseEntity<String> signup(@RequestBody RegisterDto registerDto){
        String res = authService.register(registerDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}

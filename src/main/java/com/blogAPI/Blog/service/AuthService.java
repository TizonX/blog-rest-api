package com.blogAPI.Blog.service;

import com.blogAPI.Blog.payload.LogginDto;
import com.blogAPI.Blog.payload.RegisterDto;

public interface AuthService {
    String login(LogginDto logginDto);

    String register(RegisterDto registerDto);

}

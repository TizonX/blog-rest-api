package com.blogAPI.Blog.service.Impl;

import com.blogAPI.Blog.entity.Role;
import com.blogAPI.Blog.entity.User;
import com.blogAPI.Blog.exception.BlogApiException;
import com.blogAPI.Blog.payload.LogginDto;
import com.blogAPI.Blog.payload.RegisterDto;
import com.blogAPI.Blog.repository.RoleRepository;
import com.blogAPI.Blog.repository.UserRepository;
import com.blogAPI.Blog.security.JwtTokenProvider;
import com.blogAPI.Blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository ;
    private PasswordEncoder passwordEncoder;
    // this has generated jwt token
    private JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String login(LogginDto logginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logginDto.getUsernameOrEmail(), logginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        // now we will update the user details/register user

        User user = new User();
        user.setName(registerDto.getName());;
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User Registered Successfully";
    }
}

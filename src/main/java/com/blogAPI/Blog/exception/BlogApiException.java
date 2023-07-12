package com.blogAPI.Blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;


}

package com.blogAPI.Blog.payload;

import com.blogAPI.Blog.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name can't be null")
    private String name;
    @NotEmpty(message = "Email can't be null")
    @Email
    private String email;
    @NotEmpty(message = "Body Can't be null")
    @Size(min = 3, message = "Minimum 3 character required")
    private String body;
}

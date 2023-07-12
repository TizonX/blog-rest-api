package com.blogAPI.Blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty
    @Size(min = 3, message = "Name field can't be empty")
    private String name;
    @NotEmpty
    @Email(message = "Please check email format")
    private String email;
    @NotEmpty
    @Size(min = 3, message = "Username field can't be empty")
    private String username;
    @NotBlank
    @Size(min = 3, message = "Length Should be greater than 3 character")
    private String password;

}

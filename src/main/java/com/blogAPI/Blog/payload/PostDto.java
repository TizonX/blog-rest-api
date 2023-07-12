package com.blogAPI.Blog.payload;

import com.blogAPI.Blog.entity.Comment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Title atleast 2 character")
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}

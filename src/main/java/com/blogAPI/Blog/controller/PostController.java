package com.blogAPI.Blog.controller;

import com.blogAPI.Blog.entity.Category;
import com.blogAPI.Blog.payload.PostDto;
import com.blogAPI.Blog.payload.PostResponse;
import com.blogAPI.Blog.repository.CategoryRepository;
import com.blogAPI.Blog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        PostDto postDto1 = postService.createPost(postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy)
    {
        PostResponse postDto = postService.getAllPosts(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable(name = "id") Long id)
    {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id,@Valid @RequestBody PostDto postDto)
    {
        PostDto postDto1 = postService.updatePost(id,postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable(name = "id") Long id)
    {
        postService.deletePost(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(name = "id")Long id)
    {
        List<PostDto> postDtos = postService.getPostsByCategory(id);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

}

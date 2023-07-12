package com.blogAPI.Blog.service;

import com.blogAPI.Blog.payload.PostDto;
import com.blogAPI.Blog.payload.PostResponse;

import java.util.List;

public interface PostService {

     public PostDto createPost(PostDto postDto);
     PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);
     public PostDto getPostById(Long id);
     public PostDto updatePost(Long id, PostDto postDto);
     public void deletePost(Long id);
     List<PostDto> getPostsByCategory(Long categoryId);

}

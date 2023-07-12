package com.blogAPI.Blog.service.Impl;

import com.blogAPI.Blog.entity.Category;
import com.blogAPI.Blog.entity.Post;
import com.blogAPI.Blog.exception.ResourceNotFoundException;
import com.blogAPI.Blog.payload.PostDto;
import com.blogAPI.Blog.payload.PostResponse;
import com.blogAPI.Blog.repository.CategoryRepository;
import com.blogAPI.Blog.repository.PostRepository;
import com.blogAPI.Blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public PostDto createPost(PostDto postDto) {

       Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = modelMapper.map(postDto, Post.class);
        post.setCategory(category);
        Post post1 = postRepository.save(post);

        return modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy ));
        List<PostDto> postDto = new ArrayList<>();
        Page<Post> posts = postRepository.findAll(pageable);
        posts.forEach((e)-> postDto.add(modelMapper.map(e,PostDto.class)));
        PostResponse poseResponse = new PostResponse();
        poseResponse.setContent(postDto);
        poseResponse.setPageNo(posts.getNumber());
        poseResponse.setPageSize(posts.getSize());
        poseResponse.setTotalElement(posts.getTotalElements());
        poseResponse.setTotalPages(posts.getTotalPages());
        poseResponse.setLast(posts.isLast());
        return poseResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category","id",postDto.getCategoryId()));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        Post post1 = postRepository.save(post);
        return modelMapper.map(post1, PostDto.class);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        List<Post> posts = postRepository.findByCategoryId(id);

        return posts.stream().map((e)-> modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
    }
}

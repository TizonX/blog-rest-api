package com.blogAPI.Blog.service.Impl;

import com.blogAPI.Blog.entity.Comment;
import com.blogAPI.Blog.entity.Post;
import com.blogAPI.Blog.exception.BlogApiException;
import com.blogAPI.Blog.exception.ResourceNotFoundException;
import com.blogAPI.Blog.payload.CommentDto;
import com.blogAPI.Blog.repository.CommentRepository;
import com.blogAPI.Blog.repository.PostRepository;
import com.blogAPI.Blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(Long id, CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        comment.setPost(post);
        Comment comment1 = commentRepository.save(comment);
        return modelMapper.map(comment1, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComment() {
        return null;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);

        return commentList.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","ID",commentId));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to post");
        }
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","ID",commentId));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to post");
        }
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        Comment comment1 = commentRepository.save(comment);
        return modelMapper.map(comment1, CommentDto.class);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","ID",commentId));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to post");
        }
        commentRepository.delete(comment);
    }
}

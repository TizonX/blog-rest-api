package com.blogAPI.Blog.service;

import com.blogAPI.Blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(Long id, CommentDto commentDto);
    public List<CommentDto> getAllComment();

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId );


}

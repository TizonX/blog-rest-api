package com.blogAPI.Blog.controller;

import com.blogAPI.Blog.payload.CommentDto;
import com.blogAPI.Blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long id,
                                                    @Valid @RequestBody CommentDto commentDto)
    {
    CommentDto commentDto1 =  commentService.createComment(id, commentDto);
    return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentByPostId(@PathVariable(value = "postId") Long id)
    {
        List<CommentDto> commentDto1 =  commentService.getCommentByPostId(id);
        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable(value = "postId") Long postId,
                                                               @PathVariable(value = "commentId") Long commentId)
    {
        CommentDto commentDto1 =  commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                         @PathVariable(value = "commentId") Long commentId,
                                                   @Valid @RequestBody CommentDto commentDto)
    {
        CommentDto commentDto1 =  commentService.updateComment(postId, commentId,commentDto );
        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId
                                                    )
    {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}

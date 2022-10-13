package com.sparta.week04_hw_2.controller;

import com.sparta.week04_hw_2.dto.CommentRequestDto;
import com.sparta.week04_hw_2.dto.CommentReturnDto;
import com.sparta.week04_hw_2.dto.PostDeleteDto;
import com.sparta.week04_hw_2.entity.Comment;
import com.sparta.week04_hw_2.entity.Post;
import com.sparta.week04_hw_2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/auth/comment")
    public ResponseEntity<CommentReturnDto> postComment(@RequestBody CommentRequestDto requestDto){
        return ResponseEntity.ok(commentService.commentPost(requestDto));
    }

    @GetMapping("/comment/{postid}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postid){
        return ResponseEntity.ok(commentService.getComments(postid));
    }

    @PutMapping("/auth/comment/{commentid}")
    public ResponseEntity<Comment> putComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long commentid){
        return ResponseEntity.ok(commentService.putComment(requestDto,commentid));
    }

    @DeleteMapping("/auth/comment/{commentid}")
    public ResponseEntity<PostDeleteDto> delComment(@PathVariable Long commentid){
        return ResponseEntity.ok(commentService.delComment(commentid));
    }
}

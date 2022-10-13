package com.sparta.week04_hw_2.controller;

import com.sparta.week04_hw_2.dto.PostCommentReturnDto;
import com.sparta.week04_hw_2.dto.PostDeleteDto;
import com.sparta.week04_hw_2.dto.PostRequestDto;
import com.sparta.week04_hw_2.dto.PostRetunDto;
import com.sparta.week04_hw_2.entity.Post;
import com.sparta.week04_hw_2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/auth/post")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto requestDto){
        return ResponseEntity.ok(postService.createPost(requestDto));
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostRetunDto>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post/{postid}")
    public ResponseEntity<PostCommentReturnDto> getPost(@PathVariable Long postid){
        return ResponseEntity.ok(postService.getPost(postid));
    }

    @PutMapping("/auth/post/{postid}")
    public ResponseEntity<Post> putPost(@RequestBody PostRequestDto requestDto, @PathVariable Long postid){
        return ResponseEntity.ok(postService.putPost(requestDto,postid));
    }

    @DeleteMapping("/auth/post/{postid}")
    public ResponseEntity<PostDeleteDto> delPost(@PathVariable Long postid){
        return ResponseEntity.ok(postService.delPost(postid));
    }
}

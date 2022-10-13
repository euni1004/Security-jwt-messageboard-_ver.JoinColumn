package com.sparta.week04_hw_2.service;

import com.sparta.week04_hw_2.dto.*;
import com.sparta.week04_hw_2.entity.Comment;
import com.sparta.week04_hw_2.entity.Member;
import com.sparta.week04_hw_2.entity.Post;
import com.sparta.week04_hw_2.repository.CommentRepository;
import com.sparta.week04_hw_2.repository.MemberRepository;
import com.sparta.week04_hw_2.repository.PostRepository;
import com.sparta.week04_hw_2.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Post post = new Post(requestDto.getTitle(),requestDto.getContent(), member.getNickname(),member);
        postRepository.save(post);
        return post;
    }

    public List<PostRetunDto> getPosts(){
        List<Post> posts = postRepository.findAll();
        List<PostRetunDto> allPosts = new ArrayList<>();
        for(Post post : posts){
            MemberResponseDto postmember =MemberResponseDto.of(post.getMember());
            allPosts.add(PostRetunDto.toreturnDto(post, postmember));
        }
        return allPosts;
    }

    public PostCommentReturnDto getPost(Long postid){
        Post post =postRepository.findById(postid).orElseThrow(()-> new RuntimeException("글이 존재하지 않습니다."));
        List<Comment> comments = commentRepository.findAllByPostnum(post.getPostId()).orElse(null);
        PostCommentReturnDto postCommentReturnDto = PostCommentReturnDto.topostcommentReturnDto(post, comments);
        return postCommentReturnDto;
    }

    @Transactional
    public Post putPost(PostRequestDto requestDto, Long postid){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Post post = postRepository.findById(postid).orElseThrow(()-> new RuntimeException("글이 존재하지 않습니다"));

        if(!member.getNickname().equals(post.getAuthor())){
            throw new RuntimeException("글 수정권한이 없습니다");
        }

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        postRepository.save(post);

        return post;
    }

    @Transactional
    public PostDeleteDto delPost(Long postid){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Post post = postRepository.findById(postid).orElseThrow(()-> new RuntimeException("글이 존재하지 않습니다"));

        if(!member.getNickname().equals(post.getAuthor())){
            throw new RuntimeException("글 삭제 권한이 없습니다.");
        }

        postRepository.delete(post);

        List<Comment> comments = commentRepository.findAllByPostnum(post.getPostId()).orElse(null);

        commentRepository.deleteAll(comments);

        PostDeleteDto deleteDto = new PostDeleteDto("delete success");
        return deleteDto;
    }
}

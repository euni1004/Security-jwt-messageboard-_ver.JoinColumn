package com.sparta.week04_hw_2.service;

import com.sparta.week04_hw_2.dto.CommentRequestDto;
import com.sparta.week04_hw_2.dto.CommentReturnDto;
import com.sparta.week04_hw_2.dto.PostDeleteDto;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentReturnDto commentPost(CommentRequestDto requestDto){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(()-> new RuntimeException("게시물이 존재하지 않습니다"));

        Comment comment = new Comment(requestDto.getPostId(),member.getNickname(),requestDto.getComment(), post.getPostId(), post,member);

        commentRepository.save(comment);
        CommentReturnDto returnDto = CommentReturnDto.tocommentReturnDto(member,comment);
        return returnDto;
    }

    public List<Comment> getComments(Long postid){
        Post post = postRepository.findById(postid).orElseThrow(()-> new RuntimeException("게시물이 존재하지 않습니다"));
        List<Comment>  comments = commentRepository.findAllByPostnum(post.getPostId()).orElseThrow(()-> new RuntimeException("게시물이 존재하지 않습니다."));

        return comments;
    }

    @Transactional
    public Comment putComment(CommentRequestDto requestDto, Long commentid){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Comment comment = commentRepository.findById(commentid).orElseThrow(()-> new RuntimeException("댓글이 존재하지 않습니다"));

        if(!member.getNickname().equals(comment.getAuthor())){
            throw new RuntimeException("글 수정권한이 없습니다");
        }

        comment.setComment(requestDto.getComment());
        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public PostDeleteDto delComment(Long commentid){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Comment comment = commentRepository.findById(commentid).orElseThrow(()-> new RuntimeException("댓글이 존재하지 않습니다"));

        if(!member.getNickname().equals(comment.getAuthor())){
            throw new RuntimeException("글 수정권한이 없습니다");
        }

        commentRepository.delete(comment);
        PostDeleteDto deleteDto = new PostDeleteDto("delete success");
        return deleteDto;
    }
}

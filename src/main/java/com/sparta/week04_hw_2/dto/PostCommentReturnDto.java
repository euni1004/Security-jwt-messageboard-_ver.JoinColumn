package com.sparta.week04_hw_2.dto;

import com.sparta.week04_hw_2.entity.Comment;
import com.sparta.week04_hw_2.entity.Member;
import com.sparta.week04_hw_2.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentReturnDto {
    private Long postid;
    private String title;
    private String comment;
    private String author;
    private List<Comment> commentResponseDtoList;

    public static PostCommentReturnDto topostcommentReturnDto(Post post, List<Comment> comment) {
        return new PostCommentReturnDto(post.getPostId(), post.getTitle(), post.getContent(), post.getAuthor(), comment);
    }
}

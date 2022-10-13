package com.sparta.week04_hw_2.dto;

import com.sparta.week04_hw_2.entity.Comment;
import com.sparta.week04_hw_2.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentReturnDto {

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long commentid;
    private String author;
    private String comment;

    public static CommentReturnDto tocommentReturnDto(Member member, Comment comment) {
        return new CommentReturnDto(comment.getCreatedAt(),comment.getModifiedAt(), comment.getCommentId(), member.getNickname(), comment.getComment());
    }
}

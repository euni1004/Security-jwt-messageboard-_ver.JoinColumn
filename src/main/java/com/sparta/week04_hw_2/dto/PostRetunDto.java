package com.sparta.week04_hw_2.dto;

import com.sparta.week04_hw_2.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRetunDto {

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long postid;
    private String title;
    private String content;
    private MemberResponseDto member;

    public static PostRetunDto toreturnDto(Post post, MemberResponseDto member){
        return  new PostRetunDto(post.getCreatedAt(),post.getModifiedAt(),post.getPostId(), post.getTitle(),post.getContent(),member);
    }
}

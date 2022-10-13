package com.sparta.week04_hw_2.dto;

import com.sparta.week04_hw_2.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long memberid;
    private String nickname;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getCreatedAt(),member.getModifiedAt(),member.getMemberId(), member.getNickname());
    }
}

package com.sparta.week04_hw_2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long memberid;
    private String nickname;
}

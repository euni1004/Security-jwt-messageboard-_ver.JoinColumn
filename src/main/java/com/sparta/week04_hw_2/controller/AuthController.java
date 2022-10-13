package com.sparta.week04_hw_2.controller;


import com.sparta.week04_hw_2.dto.TokenDto;
import com.sparta.week04_hw_2.dto.MemberRequestDto;
import com.sparta.week04_hw_2.dto.MemberResponseDto;
import com.sparta.week04_hw_2.dto.TokenRequestDto;
import com.sparta.week04_hw_2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(memberRequestDto);
        response.setHeader("AT",tokenDto.getAccessToken());
        response.setHeader("RT",tokenDto.getRefreshToken());
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}

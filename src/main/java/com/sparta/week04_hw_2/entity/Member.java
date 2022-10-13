package com.sparta.week04_hw_2.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends Timestamped{

    @Id
    @Column(name = "memberid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String nickname, String password, Authority authority) {
        this.nickname = nickname;
        this.password = password;
        this.authority = authority;
    }
}

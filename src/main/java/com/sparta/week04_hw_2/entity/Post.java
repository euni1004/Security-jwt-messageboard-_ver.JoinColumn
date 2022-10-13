package com.sparta.week04_hw_2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {

    @Id
    @Column(name = "postid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "memberid",nullable = false)
    private Member member;

    public Post(String title, String content, String nickname,Member member) {
        this.title = title;
        this.content = content;
        this.author = nickname;
        this.member = member;
    }

}

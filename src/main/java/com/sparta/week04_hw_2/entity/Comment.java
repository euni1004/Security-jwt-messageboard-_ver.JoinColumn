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
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String author;

    private String comment;

    private Long postnum;

    @ManyToOne
    @JoinColumn(name = "postid",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "memberid",nullable = false)
    private Member member;

}

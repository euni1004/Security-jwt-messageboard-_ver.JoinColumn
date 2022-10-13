package com.sparta.week04_hw_2.repository;

import com.sparta.week04_hw_2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<List<Comment>> findAllByPostnum(Long id);

}

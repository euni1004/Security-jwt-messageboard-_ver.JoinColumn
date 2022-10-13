package com.sparta.week04_hw_2.repository;

import com.sparta.week04_hw_2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}

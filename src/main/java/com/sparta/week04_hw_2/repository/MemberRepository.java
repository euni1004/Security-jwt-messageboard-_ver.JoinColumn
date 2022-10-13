package com.sparta.week04_hw_2.repository;

import com.sparta.week04_hw_2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

    Optional<Member> findById(Long id);
}

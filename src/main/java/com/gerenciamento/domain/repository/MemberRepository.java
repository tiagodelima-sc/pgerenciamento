package com.gerenciamento.domain.repository;

import com.gerenciamento.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByIdAndDeleted   (String id   , boolean deleted);
    Optional<Member> findByEmailAndDeleted(String email, boolean deleted);

    @Query("SELECT m FROM Member m WHERE m.deleted = false ORDER BY m.name")
    List<Member> findAllNotDeleted();
}

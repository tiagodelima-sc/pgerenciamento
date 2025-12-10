package com.gerenciamento.domain.repository;

import com.gerenciamento.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllNotDeleted_returnsOnlyActiveMembers() {
        Member activeMember = Member.builder()
                .name   ("Tiago")
                .email  ("tiago07@gmail.com")
                .secret ("XD1XHDYA1JS")
                .deleted(false)
                .build  ();

        Member deletedMember = Member.builder()
                .name   ("Mateus")
                .email  ("mateuszin@gmail.com")
                .secret ("21DAYQA1JS")
                .deleted(true)
                .build  ();

        memberRepository.save(activeMember );
        memberRepository.save(deletedMember);

        List<Member> result = memberRepository.findAllNotDeleted();

        assertEquals(1, result.size());
        assertEquals("Tiago", result.get(0).getName());
    }
}
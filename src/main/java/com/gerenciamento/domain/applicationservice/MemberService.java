package com.gerenciamento.domain.applicationservice;

import com.gerenciamento.domain.entity.Member;
import com.gerenciamento.domain.exception.MemberNotFoundException;
import com.gerenciamento.domain.repository.MemberRepository;
import com.gerenciamento.infrastructure.dto.SaveMemberDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(SaveMemberDataDTO saveMemberDataDTO) {
        Member member = Member
                .builder()
                .name(saveMemberDataDTO.getName())
                .email(saveMemberDataDTO.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);
        return member;
    }

    public Member loadMemberById(String memberId) {
        return memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public void deleteMember(String memberId) {
        Member member = loadMemberById(memberId);
        member.setDeleted(true);
    }

    @Transactional
    public Member updateMember(String memberId, SaveMemberDataDTO saveMemberDataDTO) {
        Member member = loadMemberById(memberId);

        member.setName(saveMemberDataDTO.getName());
        member.setEmail(saveMemberDataDTO.getEmail());

        return member;
    }
}

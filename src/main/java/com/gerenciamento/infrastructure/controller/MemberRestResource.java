package com.gerenciamento.infrastructure.controller;

import com.gerenciamento.domain.applicationservice.MemberService;
import com.gerenciamento.domain.entity.Member;
import com.gerenciamento.infrastructure.dto.MemberDTO;
import com.gerenciamento.infrastructure.dto.SaveMemberDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.gerenciamento.infrastructure.controller.RestConstants.PATH_MEMBERS;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
public class MemberRestResource {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid SaveMemberDataDTO saveMemberDataDTO) {
        Member member = memberService.createMember(saveMemberDataDTO);

        return ResponseEntity
                .created(URI.create(PATH_MEMBERS + member.getId()))
                .body(MemberDTO.create(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMemberById(@PathVariable("id") String memberId) {
        Member member = memberService.loadMemberById(memberId);

        return ResponseEntity.ok(MemberDTO.create(member));
    }
}

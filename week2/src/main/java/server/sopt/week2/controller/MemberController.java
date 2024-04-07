package server.sopt.week2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.dto.MemberCreateDto;
import server.sopt.week2.dto.MemberFindDto;
import server.sopt.week2.service.MemberService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberCreateDto memberCreateDto){
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDto))).build();
    }
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> getMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}

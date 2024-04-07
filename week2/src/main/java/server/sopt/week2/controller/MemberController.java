package server.sopt.week2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.sopt.week2.dto.MemberCreateDto;
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
}

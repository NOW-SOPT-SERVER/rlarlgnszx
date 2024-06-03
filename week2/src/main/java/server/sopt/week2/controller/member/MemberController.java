package server.sopt.week2.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.dto.UserJoinResponse;
import server.sopt.week2.dto.member.MemberCreateDto;
import server.sopt.week2.dto.member.MemberFindDto;
import server.sopt.week2.service.MemberService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;
//    private final LogoutService authService;
    @PostMapping
    public ResponseEntity<UserJoinResponse> postMember(
            @RequestBody MemberCreateDto memberCreate
    ) {
        UserJoinResponse userJoinResponse = memberService.createMember(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", userJoinResponse.userId())
                .body(
                        userJoinResponse
                );
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

    @GetMapping()
    public List<MemberFindDto> getAllMember(){
        return memberService.getAllMember();
    }

//    @PostMapping("/refreshs")
//    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        LogoutService.logout(request, response);
//        return ResponseEntity.noContent().build();
//    }
}

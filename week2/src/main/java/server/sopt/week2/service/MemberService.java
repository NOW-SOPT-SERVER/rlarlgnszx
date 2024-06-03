package server.sopt.week2.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.sopt.week2.auth.UserAuthentication;
import server.sopt.week2.auth.redis.domain.Token;
import server.sopt.week2.auth.redis.service.RefreshTokenService;
import server.sopt.week2.auth.repository.RedisTokenRepository;
import server.sopt.week2.common.jwt.JwtTokenProvider;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.UserJoinResponse;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.dto.member.MemberCreateDto;
import server.sopt.week2.dto.member.MemberFindDto;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.exception.NotFoundException;
import server.sopt.week2.repo.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    @Transactional
    public UserJoinResponse createMember(MemberCreateDto memberCreateDto) {
        Member member = memberRepository.save(Member.create(memberCreateDto.name(),memberCreateDto.part(),memberCreateDto.age()));
        Long memberId = member.getId();
        UserAuthentication userAuthentication = UserAuthentication.createUserAuthentication(memberId);
        String accessToken = jwtTokenProvider.issueAccessToken(
                userAuthentication
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                userAuthentication
        );
        refreshTokenService.save(memberId,refreshToken);
        return UserJoinResponse.of(accessToken, refreshToken,memberId.toString());
    }
    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new BusinessException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));
    }

    public MemberFindDto getMemberById(Long memberId) {
        return MemberFindDto.of(findMemberById(memberId));
    }

    public List<MemberFindDto> getAllMember() {
        return memberRepository.findAll()
                .stream().map(MemberFindDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.delete(findMemberById(memberId));
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));
    }
}

package server.sopt.week2.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.MemberCreateDto;
import server.sopt.week2.dto.MemberFindDto;
import server.sopt.week2.repo.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public String createMember(MemberCreateDto memberCreateDto) {
        Member member = memberRepository.save(Member.create(memberCreateDto.name(),memberCreateDto.part(),memberCreateDto.age()));
        return member.getId().toString();
    }
    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("NO MATCH ID FOR USER"));
    }

    public MemberFindDto getMemberById(Long memberId) {
        return MemberFindDto.of(findMemberById(memberId));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.delete(findMemberById(memberId));
    }
}

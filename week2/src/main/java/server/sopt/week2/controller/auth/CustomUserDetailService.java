package server.sopt.week2.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.repo.MemberRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String socialId) throws UsernameNotFoundException {
        return memberRepository.findBySocialId(socialId).orElseThrow(
                () -> new BusinessException(ErrorMessage.KEY_NOT_FOUND)
        );
    }
}

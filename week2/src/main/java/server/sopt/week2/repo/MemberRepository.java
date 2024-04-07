package server.sopt.week2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.MemberCreateDto;


public interface MemberRepository extends JpaRepository<Member,Long> {
}

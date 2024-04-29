package server.sopt.week2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.week2.domain.Member;


public interface MemberRepository extends JpaRepository<Member,Long> {
}

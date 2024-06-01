package server.sopt.week2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.sopt.week2.domain.Member;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findBySocialId(String socialId);
}

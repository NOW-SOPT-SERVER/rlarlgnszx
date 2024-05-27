package server.sopt.week2.auth.repository;


import org.springframework.data.repository.CrudRepository;
import server.sopt.week2.auth.redis.domain.Token;

import java.util.Optional;

public interface TokenBlackListRepository extends CrudRepository<Token, Long> {
    Optional<Token> findBy(final String accessToken);
    Optional<Token> findById(final Long memberId);
}
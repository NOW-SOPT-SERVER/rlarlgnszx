package server.sopt.week2.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import server.sopt.week2.auth.redis.domain.Token;

import java.util.Optional;

//@Repository
public interface RedisTokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByRefreshToken(final String refreshToken);
    Optional<Token> findById(final Long memberId);
}

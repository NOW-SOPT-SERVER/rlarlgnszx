package server.sopt.week2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Member;

import java.util.Optional;

public interface BlogRepostiory  extends JpaRepository<Blog,Long> {
}
package server.sopt.week2.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Post;
import server.sopt.week2.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface PostRepostiory extends JpaRepository<Post,Long> {
    Optional<Post> findByIdAndBlog(Long postId,Blog blog);

    List<Post> findAllByBlog(Blog blog);
}

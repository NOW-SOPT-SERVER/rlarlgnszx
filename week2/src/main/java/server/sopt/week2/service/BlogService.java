package server.sopt.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.blog.BlogCreateRequest;
import server.sopt.week2.dto.blog.BlogFindDto;
import server.sopt.week2.dto.blog.BlogTitleUpdateRequeset;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.exception.NotFoundException;
import server.sopt.week2.repo.BlogRepostiory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepostiory blogRepostiory;
    private final MemberService memberService;

    public String create(Long memberId, BlogCreateRequest blogCreateRequest){
        Member member = memberService.findById(memberId);
        Blog blog = blogRepostiory.save(Blog.create(member, blogCreateRequest));
        return blog.getId().toString();
    }

    public void updateTtile(Long blogId, BlogTitleUpdateRequeset blogTitleUpdateRequeset) {
        Blog blog = blogRepostiory.findById(blogId).orElseThrow(
                () -> new BusinessException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION)
        );
        blog.setTitle(blogTitleUpdateRequeset.title());
    }

    public List<BlogFindDto> getAllBlog() {
        return blogRepostiory.findAll()
                .stream().map(BlogFindDto::of)
                .collect(Collectors.toList());
    }

    public Blog findbyId(Long blogId) {
        return blogRepostiory.findById(blogId)
                .orElseThrow(
                        () -> new BusinessException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION)
                );
    }
}

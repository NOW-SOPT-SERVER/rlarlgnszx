package server.sopt.week2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.BlogCreateRequest;
import server.sopt.week2.dto.BlogTitleUpdateRequeset;
import server.sopt.week2.dto.ErrorMessage;
import server.sopt.week2.exception.NotFoundException;
import server.sopt.week2.repo.BlogRepostiory;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepostiory blogRepostiory;
    private final MemberService memberService;

    public String create(Long memberId,BlogCreateRequest blogCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepostiory.save(Blog.create(member, blogCreateRequest));
        return blog.getId().toString();
    }

    public void updateTtile(Long blogId, BlogTitleUpdateRequeset blogTitleUpdateRequeset) {
        Blog blog = blogRepostiory.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION)
        );
        blog.setTitle(blogTitleUpdateRequeset.title());
    }
}

package server.sopt.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.blog.BlogCreateRequest;
import server.sopt.week2.dto.blog.BlogFindDto;
import server.sopt.week2.dto.blog.BlogTitleUpdateRequeset;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.exception.NotFoundException;
import server.sopt.week2.external.S3Service;
import server.sopt.week2.repo.BlogRepostiory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepostiory blogRepostiory;
    private final MemberService memberService;
    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLER = "blog/";

    public String create(Long memberId, BlogCreateRequest blogCreateRequest){
        Member member = memberService.findById(memberId);
        try {
            Blog blog = blogRepostiory.save(Blog.create(member, blogCreateRequest.title(), blogCreateRequest.description(),
                    s3Service.uploadImage(BLOG_S3_UPLOAD_FOLER, blogCreateRequest.image())));
            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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

    public void deleteBlogImageUrl(Long blogId) throws IOException {
        Blog blog = blogRepostiory.findById(blogId).orElseThrow(
                () -> new BusinessException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION)
        );
        if (blog.getImageUrl() != null) {
            blog.setImageUrl(null);
            s3Service.deleteImage(blog.getImageUrl());
        }else{
            throw new RuntimeException("Blog " + blogId.toString() + "Not Have ImageURL");
        }
    }
}

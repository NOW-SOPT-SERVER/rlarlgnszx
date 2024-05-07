package server.sopt.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.week2.domain.Blog;
import server.sopt.week2.domain.Member;
import server.sopt.week2.domain.Post;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.dto.post.PostCreateRequest;
import server.sopt.week2.dto.post.PostFindDto;
import server.sopt.week2.dto.post.PostUpdateRequest;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.exception.NotFoundException;
import server.sopt.week2.repo.PostRepostiory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepostiory postRepository;
    private final BlogService blogService;

    public String create(Long blogId, PostCreateRequest postCreateRequest) {
        Blog blog = blogService.findbyId(blogId);
        Post post = postRepository.save(Post.create(blog, postCreateRequest));
        return post.getId().toString();
    }

    public void updatePost(Long postId, PostUpdateRequest postUpdateRequest,Long blogId) {
        Blog blog = blogService.findbyId(blogId);
        Post post = postRepository.findByIdAndBlog(postId, blog)
                .orElseThrow(
                        () -> new BusinessException(ErrorMessage.POST_AND_BLOG_NOT_MATCH_EXCEPTION)
                );
        System.out.println(post.getContent());
        post.setTitle(postUpdateRequest.title());
        post.setContent(postUpdateRequest.content());
    }

    public List<PostFindDto> getAllPost() {
        return postRepository.findAll()
                .stream().map(PostFindDto::of)
                .collect(Collectors.toList());
    }

    public List<PostFindDto> getAllPostAboutBlogId(Long blogId) {
        Blog blog = blogService.findbyId(blogId);
        return postRepository.findAllByBlog(blog)
                .stream().map(PostFindDto::of)
                .collect(Collectors.toList());
    }

}

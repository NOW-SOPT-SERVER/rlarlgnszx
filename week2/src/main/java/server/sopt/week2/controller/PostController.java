package server.sopt.week2.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.dto.post.PostCreateRequest;
import server.sopt.week2.dto.post.PostFindDto;
import server.sopt.week2.dto.post.PostUpdateRequest;
import server.sopt.week2.service.PostService;
import server.sopt.week2.success.SuccessMessage;
import server.sopt.week2.success.SuccessStatusResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Transactional
    @PostMapping("/post")
    public ResponseEntity<SuccessStatusResponse> createPost(
            @RequestHeader Long blogId,
            @RequestBody PostCreateRequest postCreateRequest) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .header("Location", postService.create(blogId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POST_CREATE_SUCCESS));
    }
    @GetMapping("/posts")
    public List<PostFindDto> getAllPost(){
        return postService.getAllPost();
    }


    @GetMapping("/posts/{blogId}")
    public List<PostFindDto> getAllPost(@PathVariable Long blogId) {
        return postService.getAllPostAboutBlogId(blogId);
    }

    @Transactional
    @PatchMapping("/post/{postId}")
    public ResponseEntity updateBlogTitle(
            @RequestHeader Long blogId,
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequest postUpdateRequest
    ){
        postService.updatePost(postId, postUpdateRequest,blogId);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                SuccessStatusResponse.of(SuccessMessage.POST_UPDATE_SUCCESS)
        );
    }
}

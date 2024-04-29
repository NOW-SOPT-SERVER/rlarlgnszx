package server.sopt.week2.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.dto.blog.BlogCreateRequest;
import server.sopt.week2.dto.blog.BlogFindDto;
import server.sopt.week2.dto.blog.BlogTitleUpdateRequeset;
import server.sopt.week2.service.BlogService;
import server.sopt.week2.success.SuccessMessage;
import server.sopt.week2.success.SuccessStatusResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogs")
    public List<BlogFindDto> getAllBlog(){
        return blogService.getAllBlog();
    }

    @Transactional
    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader Long memberId,
            @RequestBody BlogCreateRequest blogCreateRequest) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .header("Location", blogService.create(memberId, blogCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));

    }

    @Transactional
    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @RequestBody @Valid BlogTitleUpdateRequeset blogTitleUpdateRequeset
    ){
        blogService.updateTtile(blogId, blogTitleUpdateRequeset);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                SuccessStatusResponse.of(SuccessMessage.BLOG_UPDATE_SUCCESS)
        );
    }

}

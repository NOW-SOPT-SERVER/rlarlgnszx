package server.sopt.week2.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.dto.BlogCreateRequest;
import server.sopt.week2.dto.BlogTitleUpdateRequeset;
import server.sopt.week2.dto.SuccessMessage;
import server.sopt.week2.dto.SuccessStatusResponse;
import server.sopt.week2.service.BlogService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {


    private final BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader Long memberId,
            @RequestBody BlogCreateRequest blogCreateRequest) {
        return ResponseEntity.status(
                        HttpStatus.CREATED
                )
                .header("Locahtion", blogService.create(memberId, blogCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));

    }

    @Transactional
    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @RequestBody @Valid BlogTitleUpdateRequeset blogTitleUpdateRequeset
    ){
        blogService.updateTtile(blogId, blogTitleUpdateRequeset);
        return ResponseEntity.noContent().build();
    }
}

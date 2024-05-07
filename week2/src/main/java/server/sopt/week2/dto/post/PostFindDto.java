package server.sopt.week2.dto.post;

import server.sopt.week2.domain.Post;

public record PostFindDto(
        String title, String content
        ) {
    public static PostFindDto of(Post post){
        return new PostFindDto(post.getTitle(), post.getContent());
    }
}

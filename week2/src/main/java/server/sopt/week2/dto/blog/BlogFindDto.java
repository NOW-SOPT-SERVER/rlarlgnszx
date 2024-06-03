package server.sopt.week2.dto.blog;

import server.sopt.week2.domain.Blog;

public record BlogFindDto(
        String title, String description
) {
    public static BlogFindDto of(Blog blog) {
        return new BlogFindDto(blog.getTitle(), blog.getDescription());
    }
}

package server.sopt.week2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.sopt.week2.dto.post.PostCreateRequest;

import java.util.Optional;

@Entity
@Getter

@NoArgsConstructor
public class Post  extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    public Post(String title, String content, Blog blog) {
        this.title = title;
        this.content = content;
        this.blog = blog;
    }

    public static Post create(Blog blog, PostCreateRequest postCreateRequest) {
        return new Post(postCreateRequest.title(), postCreateRequest.content(),blog);
    }

}
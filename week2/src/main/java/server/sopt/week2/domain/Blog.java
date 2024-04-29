package server.sopt.week2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.sopt.week2.dto.blog.BlogCreateRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class Blog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Setter
    @Column(length = 200)
    private String title;


    @Setter
    private String description;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    private Blog(Member member, String title, String description) {

        this.member = member;
        this.title = title;
        this.description = description;
    }
    public static Blog create(Member member, BlogCreateRequest blogCreateRequest) {
        return new Blog(member, blogCreateRequest.title(), blogCreateRequest.description());
    }


//
//    @CreatedDate
//    private LocalDateTime createAt;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;

}

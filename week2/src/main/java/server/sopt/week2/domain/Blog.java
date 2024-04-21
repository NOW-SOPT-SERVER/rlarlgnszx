package server.sopt.week2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import server.sopt.week2.domain.Member;
import server.sopt.week2.dto.BlogCreateRequest;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class Blog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 200)
    private String title;

    private String description;

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

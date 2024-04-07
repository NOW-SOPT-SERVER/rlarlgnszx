package server.sopt.week2.domain;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
//@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)

    private Part part;

    private int age;

    public static Member create(String name, Part part, int age) {
        return Member.builder()
                .name(name)
                .part(part)
                .age(age)
                .build();
    }
    @Builder
    public Member(String name, Part part, int age) {
        this.name = name;
        this.part = part;
        this.age = age;
    }
}


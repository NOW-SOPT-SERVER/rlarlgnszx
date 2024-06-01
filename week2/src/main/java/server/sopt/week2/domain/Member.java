package server.sopt.week2.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String socialId; // 로그인 식별키

    @Enumerated(EnumType.STRING)
    private Part part;

    @Min(7)
    private int age;

    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    private List<String> roles = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Blog blog;

    @Builder
    public Member(String socialId, Part part, int age, List<String> roles, Blog blog) {
        this.socialId = socialId;
        this.part = part;
        this.age = age;
        this.roles = roles;
    }


    public static Member create(String socialId, Part part, int age) {
        return Member.builder()
                .socialId(socialId)
                .part(part)
                .age(age)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.socialId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


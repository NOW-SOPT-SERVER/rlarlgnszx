package server.sopt.week2.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성 완료"),
    POST_CREATE_SUCCESS(HttpStatus.CREATED.value(),"포스트 생성 완료"),
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(),"멤버 생성 완료"),

    BLOG_UPDATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 업데이트"),
    POST_UPDATE_SUCCESS(HttpStatus.CREATED.value(), "포스트 업데이트"),
    MEMBER_UPDATE_SUCCESS(HttpStatus.CREATED.value(), "멤버 업데이트");

    private final int status;
    private final String message;
}

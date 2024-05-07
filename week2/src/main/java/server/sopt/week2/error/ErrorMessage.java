package server.sopt.week2.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    MEMBER_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사람이 없습니다"),
    BLOG_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 블로그가 없습니다"),
    POST_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 포스트가 없습니다"),
    BLOG_ID_DUPLICATED_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "블로그가 이미 존재합니다"),
    POST_AND_BLOG_NOT_MATCH_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "포스트와 블로그 ID 가일치하지 않습니다."),

    ;
    private final int status;
    private final String message;

    public String getMessage(Exception e) {
        return getMessage(e.getMessage() + "-" + this.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage());
    }
}


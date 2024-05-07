package server.sopt.week2.dto.post;

import jakarta.validation.constraints.Size;

public record PostUpdateRequest(
        @Size(max = 10, message = "최대 글자 10글자")
        String title,
        String content
) {
}

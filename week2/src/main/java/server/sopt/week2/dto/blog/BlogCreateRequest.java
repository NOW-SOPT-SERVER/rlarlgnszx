package server.sopt.week2.dto.blog;

import jakarta.validation.constraints.Size;

public record BlogCreateRequest(
        @Size(max = 10, message = "최대 글자 10글자")
        String title,String description
) {
}

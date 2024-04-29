package server.sopt.week2.success;

import lombok.Builder;

@Builder
public record SuccessStatusResponse(
        int status,
        String message
) {
    public static SuccessStatusResponse of(SuccessMessage successMessage) {
        return SuccessStatusResponse.builder()
                .message(successMessage.getMessage())
                .status(successMessage.getStatus())
                .build();
    }

}

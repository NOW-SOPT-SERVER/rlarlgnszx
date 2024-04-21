package server.sopt.week2.dto;

public record ErrorStatusResponse(int status, String message) {
    public static ErrorStatusResponse of(int status, String message) {
        return new ErrorStatusResponse(status, message);
    }

    public static ErrorStatusResponse of(ErrorMessage errorMessage) {
        return new ErrorStatusResponse(errorMessage.getStatus(), errorMessage.getMessage());
    }
}

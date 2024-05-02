package server.sopt.carrot.exception;

import lombok.Getter;
import server.sopt.carrot.error.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorMessage errorMessage;
    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

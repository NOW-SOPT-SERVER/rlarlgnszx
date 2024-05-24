package server.sopt.carrot.exception;


import server.sopt.carrot.error.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

package server.sopt.carrot.exception;


import server.sopt.carrot.error.ErrorMessage;

public class NotFoundPlaceException extends BusinessException {
    public NotFoundPlaceException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

package server.sopt.carrot.handler;
import org.hibernate.JDBCException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.sopt.carrot.error.ErrorMessage;
import server.sopt.carrot.error.ErrorStatusResponse;
import server.sopt.carrot.exception.BusinessException;
import server.sopt.carrot.exception.NotFoundException;
import server.sopt.carrot.exception.NotFoundPlaceException;

import java.util.Objects;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorStatusResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorStatusResponse.of(HttpStatus.BAD_REQUEST.value(),Objects.requireNonNull(e.getBindingResult().getFieldError().getDefaultMessage())));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorStatusResponse> blogIdDuplicateException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorStatusResponse.of(HttpStatus.BAD_REQUEST.value(),ErrorMessage.WRONG_REQUEST_FROM_YOU.getMessage()));
    }

    @ExceptionHandler(NotFoundPlaceException.class)
    protected ResponseEntity<ErrorStatusResponse> handlerNotFoundPlace(NotFoundPlaceException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStatusResponse.of(ErrorMessage.PLACE_NOT_FOUND));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorStatusResponse> handlerNotFoundInService(BusinessException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStatusResponse.of(e.getErrorMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorStatusResponse> handleEntityNotFountException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStatusResponse.of(ErrorMessage.WRONG_REQUEST_FROM_YOU));
    }


    @ExceptionHandler(JDBCException.class)
    protected ResponseEntity<ErrorStatusResponse> sqlException(JDBCException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorStatusResponse.of(e.getErrorCode() , e.getSQLState()));
    }
    //내 잘못이 아닐꺼야 하는 BAD REQUEST
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorStatusResponse> globalException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorStatusResponse.of(HttpStatus.BAD_REQUEST.value() , e.getMessage()));
    }
}

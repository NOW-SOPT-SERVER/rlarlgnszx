package server.sopt.week2.common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.sopt.week2.dto.ErrorMessage;
import server.sopt.week2.dto.ErrorStatusResponse;
import server.sopt.week2.exception.NotFoundException;
import java.util.Objects;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorStatusResponse> handleEntityNotFountException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStatusResponse.of(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorStatusResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorStatusResponse.of(HttpStatus.BAD_REQUEST.value(),Objects.requireNonNull(e.getBindingResult().getFieldError().getDefaultMessage())));
    }

}

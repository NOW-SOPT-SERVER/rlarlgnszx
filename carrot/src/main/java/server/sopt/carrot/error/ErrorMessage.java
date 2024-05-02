package server.sopt.carrot.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    //   Message
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "멤버가 없어요"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "상품이 없어요"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "주문내역이 없어요"),
    PLACE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "잘못된 장소입니다"),

    WRONG_REQUEST_FROM_YOU(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    NOT_MATCH_PRODUCT_CELLER_AND_PRODUCT(HttpStatus.BAD_REQUEST.value(), "해당 상품의 주인이 아닙니다");
    private final int status;
    private final String message;
}

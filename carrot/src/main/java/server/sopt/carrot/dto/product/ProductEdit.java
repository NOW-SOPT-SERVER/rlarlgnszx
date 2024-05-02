package server.sopt.carrot.dto.product;

import lombok.*;

public class ProductEdit {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private String itemName;
        private String itemDescription;
        private Integer price;
    }
}

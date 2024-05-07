package server.sopt.carrot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.sopt.carrot.constant.CellingStatus;

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

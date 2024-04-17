package server.sopt.carrot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.sopt.carrot.entity.OrderProcessor;

public class OrderCreate {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private Long customerWhoSellId;
        @NotNull
        private Long customerWhoBuyId;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long customerWhoSellId;
        private Long customerWhoBuyId;
        private Long productId;
        public static Response fromEntity(OrderProcessor orderProcessor) {
            return Response.builder()
                    .customerWhoBuyId(orderProcessor.getCustomerWhoBuyId())
                    .customerWhoSellId(orderProcessor.getCustomerWhoSellId())
                    .productId(orderProcessor.getProductId())
                    .build();
        }
    }
}

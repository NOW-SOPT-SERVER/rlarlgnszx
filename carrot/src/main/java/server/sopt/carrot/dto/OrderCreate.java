package server.sopt.carrot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.entity.Order;

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
        @NotNull
        private Long productId;
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
        public static Response fromEntity(Order order) {
            return Response.builder()
                    .customerWhoBuyId(order.getCustomerWhoBuyId())
                    .customerWhoSellId(order.getCustomerWhoSellId())
                    .productId(order.getProductId())
                    .build();
        }
    }
}

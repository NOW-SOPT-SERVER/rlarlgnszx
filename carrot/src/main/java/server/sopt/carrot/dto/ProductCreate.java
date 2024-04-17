package server.sopt.carrot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.entity.Product;

public class ProductCreate
{
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder

    public static class Request {
        @NotNull
        @Min(-1)
        private Integer price;

        @NotNull
        @Size(min=3,max=50,message= "Name is 3~50")
        private String itemName;

        @NotNull
        private Long customerId;

        @NotNull
        private CellingStatus cellingStatus;

        @NotNull
        @Size(min=1,max=512,message= "Description is 3~50")
        private String itemDescription;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Integer price;
        private String itemName;
        private Long customerId;
        private String itemDescription;
        private CellingStatus cellingStatus;

        public static Response fromEntity(Product product) {
            return Response.builder()
                    .price(product.getPrice())
                    .itemName(product.getItemName())
                    .cellingStatus(product.getCellingStatus())
                    .customerId(product.getCustomerId())
                    .itemDescription(product.getItemDescription())
                    .build();
        }

    }
}

package server.sopt.carrot.dto.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.constant.Place;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.entity.Product;

@Getter
public class ProductCreate
{
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotNull
        @Min(-1)
        private Integer price;

        @NotNull
        @Size(min = 3, max = 50, message = "Name is 3~50")
        private String itemName;

        @NotNull
        private Long customerId;


        @NotNull
        @Size(min = 1, max = 512, message = "Description is 3~50")
        private String itemDescription;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Place place;

        private MultipartFile image;

    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Integer price;
        private String itemName;
//       후에 id값으로 바꿀수도있음 문제발생시
        private Customer customer;
        private String itemDescription;
        private CellingStatus cellingStatus;
        private Place place;

        public static Response fromEntity(Product product) {
            return Response.builder()
                    .price(product.getPrice())
                    .itemName(product.getItemName())
                    .cellingStatus(product.getCellingStatus())
                    .customer(product.getCustomer())
                    .itemDescription(product.getItemDescription())
                    .place(product.getPlace())
                    .build();
        }

    }
}

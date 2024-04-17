package server.sopt.carrot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import server.sopt.carrot.entity.Customer;

public class CustomerCreate{
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        @Min(-1)
        @Max(99)
        private Integer age;

        @NotNull
        @Size(min=3,max=50,message= "Name is 3~50")
        private String name;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Integer age;
        private String name;

        public static Response fromEntity(Customer customer) {
            return Response.builder()
                    .age(customer.getAge())
                    .name(customer.getName())
                    .build();
        }
    }
}

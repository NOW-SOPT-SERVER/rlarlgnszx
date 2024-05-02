package server.sopt.carrot.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.constant.Place;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.entity.Product;

public record ProductFindDto(
        Long productId, String itemName, int price , String itemDescription , CellingStatus cellingStatus, Place place, int good,
        Long customerId
        ) {
    public static ProductFindDto of(Product product) {
        return new ProductFindDto(product.getId(),product.getItemName(), product.getPrice(),product.getItemDescription(), product.getCellingStatus(),product.getPlace(),product.getGood(),product.getCustomer().getId());
    }
}

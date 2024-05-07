package server.sopt.carrot.dto;

import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.entity.Product;

public record ProductFindDto(
        String itemName,int price , String itemDescription , CellingStatus cellingStatus
) {
    public static ProductFindDto of(Product product) {
        return new ProductFindDto(product.getItemName(), product.getPrice(),product.getItemDescription(), product.getCellingStatus());
    }
}

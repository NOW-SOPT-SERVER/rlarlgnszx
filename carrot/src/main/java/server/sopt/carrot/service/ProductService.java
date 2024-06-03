package server.sopt.carrot.service;

import org.springframework.http.ResponseEntity;
import server.sopt.carrot.constant.Place;
import server.sopt.carrot.dto.product.*;
import server.sopt.carrot.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Product findProductById(Long id);
    ProductFindDto getProductById(Long id);
    ProductCreate.Response createProductwithCustomerId(ProductCreate.Request req) throws IOException;

    List<ProductFindDto> getAllProductByCustomerId(Long customerId);

    List<ProductFindDto> getAllNotSellProduct();

    ProductFindDto editProduct(Long productId, ProductEdit.Request req);

    ProductFindDto soldProduct(Product product);

    List<ProductFindDto> getProductbyPlace(Place place);

    ProductFindDto productGoodUporDown(Long productId, ProductGoodUpdateDto productGoodUpdateDto);

    ResponseEntity<Void> deleteProduct(Long productId);
}

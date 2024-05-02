package server.sopt.carrot.service;

import server.sopt.carrot.constant.Place;
import server.sopt.carrot.dto.product.ProductCreate;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.dto.product.ProductEdit;
import server.sopt.carrot.dto.product.ProductGoodUpdateDto;
import server.sopt.carrot.entity.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long id);
    ProductFindDto getProductById(Long id);
    ProductCreate.Response createProductwithCustomerId(ProductCreate.Request req);

    List<ProductFindDto> getAllProductByCustomerId(Long customerId);

    List<ProductFindDto> getAllNotSellProduct();

    ProductFindDto editProduct(Long productId, ProductEdit.Request req);

    ProductFindDto soldProduct(Product product);

    List<ProductFindDto> getProductbyPlace(Place place);

    ProductFindDto productGoodUporDown(Long productId, ProductGoodUpdateDto productGoodUpdateDto);
}

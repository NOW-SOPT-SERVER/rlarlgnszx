package server.sopt.carrot.service;

import server.sopt.carrot.dto.ProductCreate;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.dto.ProductEdit;

import java.util.List;

public interface ProductService {

    ProductFindDto getProductById(Long id);
    ProductCreate.Response createProductwithCustomerId(ProductCreate.Request req);

    List<ProductFindDto> getAllProductByCustomerId(Long customerId);

    List<ProductFindDto> getAllNotSellProduct();

    ProductFindDto editProduct(Long productId, ProductEdit.Request req);

    ProductFindDto soldProduct(Long productId);
}

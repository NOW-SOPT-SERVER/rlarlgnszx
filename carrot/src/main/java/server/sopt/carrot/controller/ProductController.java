package server.sopt.carrot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.carrot.constant.Place;
import server.sopt.carrot.dto.product.ProductCreate;
import server.sopt.carrot.dto.product.ProductEdit;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.dto.product.ProductGoodUpdateDto;
import server.sopt.carrot.mapper.ProductMapper;
import server.sopt.carrot.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    // 새로운 상품을 등록해줘!
    @PostMapping("")
    public ProductCreate.Response createProduct(
            @Valid @RequestBody ProductCreate.Request req
    ) {
        return productService.createProductwithCustomerId(req);
    }
    // 특정 상품 조회
    @PatchMapping("/{productId}")
    public ProductFindDto productGoodUporDown(
            @PathVariable Long productId,
            @Valid @RequestBody ProductGoodUpdateDto productGoodUpdateDto
    ) {
        return productService.productGoodUporDown(productId,productGoodUpdateDto);
    }

    @GetMapping("/{productId}")
    public ProductFindDto getProduct(
            @PathVariable Long productId
    ) {
        return productMapper.toProductFindDto(productService.findProductById(productId));
    }

    @GetMapping("")
    public List<ProductFindDto> getAllProductbyCustomerId(
            @RequestHeader Long customerId
    ) {
        return productService.getAllProductByCustomerId(customerId);
    }


    @GetMapping("/not-sold")
    public List<ProductFindDto> getNotSoldProducts(
    ) {
        return productService.getAllNotSellProduct();
    }

    @GetMapping("/place/{place}")
    public List<ProductFindDto> getProductByPlace(
            @PathVariable Place place
    ) {
        return productService.getProductbyPlace(place);
    }

//    Product의 이름이나 dsecription, 가격등 변경가능
    @PatchMapping("/{productId}/edit")
    public ProductFindDto sellProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductEdit.Request req
    ) {
        return productService.editProduct(productId, req);
    }
}

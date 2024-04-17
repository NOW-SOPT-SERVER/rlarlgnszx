package server.sopt.carrot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.sopt.carrot.dto.*;
import server.sopt.carrot.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    // 새로운 상품을 등록해줘!
    @PostMapping("")
    public ProductCreate.Response createProduct(
            @Valid @RequestBody ProductCreate.Request req
    ) {
        return productService.createProductwithCustomerId(req);
    }

    // 특정 상품 조회
    @GetMapping("/{productId}")
    public ProductFindDto getProduct(
            @PathVariable Long productId
    ) {
        return productService.getProductById(productId);
    }


    // 구매안한 상품들을 전부 보여줘! : 사용자 view에 사용할려고 ? 후에 개선한다면 ?
    @GetMapping("/not-sold")
    public List<ProductFindDto> getNotSoldProducts(
    ) {
        return productService.getAllNotSellProduct();
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

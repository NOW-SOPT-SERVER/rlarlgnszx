package server.sopt.carrot.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.dto.ProductEdit;
import server.sopt.carrot.entity.Product;
import server.sopt.carrot.dto.ProductCreate;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductFindDto getProductById(Long id) {
        return productRepository.getProductById(id);
    }
    @Transactional
    @Override
    public ProductCreate.Response createProductwithCustomerId(ProductCreate.Request req)
    {
        Product product = Product.builder()
                .price(req.getPrice())
                .cellingStatus(CellingStatus.NOT_SOLD)
                .itemName(req.getItemName())
                .customerId(req.getCustomerId())
                .itemDescription(req.getItemDescription())
                .build();
        productRepository.save(product);
        return ProductCreate.Response.fromEntity(product);
    }

    @Override
    public List<ProductFindDto> getAllProductByCustomerId(Long customerId) {
        return productRepository.findProductsByCustomerId(customerId)
                .stream().map(ProductFindDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductFindDto> getAllNotSellProduct() {
        return productRepository.findProductsByCellingStatusEquals(CellingStatus.NOT_SOLD)
                .stream().map(ProductFindDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductFindDto editProduct(
            Long productId,
            ProductEdit.@Valid Request req) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found Product"));
        product.setPrice(req.getPrice());
        product.setItemDescription(req.getItemDescription());
        product.setItemName(req.getItemName());
        return ProductFindDto.of(product);
    }

    @Override
    @Transactional
    public ProductFindDto soldProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found Product"));
        product.setCellingStatus(CellingStatus.SOLD);
        return ProductFindDto.of(product);
    }
}

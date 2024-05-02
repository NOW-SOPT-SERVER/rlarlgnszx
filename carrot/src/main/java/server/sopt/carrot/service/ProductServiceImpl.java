package server.sopt.carrot.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.constant.Place;
import server.sopt.carrot.dto.product.ProductEdit;
import server.sopt.carrot.dto.product.ProductGoodUpdateDto;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.entity.Product;
import server.sopt.carrot.dto.product.ProductCreate;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.error.ErrorMessage;
import server.sopt.carrot.exception.BusinessException;
import server.sopt.carrot.exception.NotFoundPlaceException;
import server.sopt.carrot.mapper.ProductMapper;
import server.sopt.carrot.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CustomerService customerService;
    private final ProductMapper productMapper;

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new BusinessException(ErrorMessage.PRODUCT_NOT_FOUND)
                );
    }
    @Override
    public ProductFindDto getProductById(Long id) {
        return productMapper.toProductFindDto(productRepository.getProductById(id));
    }
    @Transactional
    @Override
    public ProductCreate.Response createProductwithCustomerId(ProductCreate.Request req)
    {
        Customer customer = customerService.getCustomerById(req.getCustomerId());
        Product product = Product.builder()
                .price(req.getPrice())
                .cellingStatus(CellingStatus.NOT_SOLD)
                .itemName(req.getItemName())
                .customer(customer)
                .itemDescription(req.getItemDescription())
                .place(req.getPlace())
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
        product.setPrice(req.getPrice() !=null ? req.getPrice() : product.getPrice());
        product.setItemName(req.getItemName() !=null ? req.getItemName() : product.getItemName());
        product.setItemDescription(req.getItemDescription()!=null ? req.getItemDescription() : product.getItemDescription());
        return ProductFindDto.of(product);
    }

    @Override
    @Transactional
    public ProductFindDto soldProduct(Product product) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new BusinessException(ErrorMessage.PRODUC)T_NOT_FOUND));
        product.setCellingStatus(CellingStatus.SOLD);
        return ProductFindDto.of(product);
    }

    @Override
    @Transactional
    public List<ProductFindDto> getProductbyPlace(Place place) throws NotFoundPlaceException {
        return productRepository.findProductsByPlace(place).stream().map(ProductFindDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductFindDto productGoodUporDown(Long productId, ProductGoodUpdateDto productGoodUpdateDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BusinessException(ErrorMessage.PRODUCT_NOT_FOUND)
        );
        int point = productGoodUpdateDto.UporDown() ? 1 : -1;
        product.setGood(product.getGood() + point);
        return productMapper.toProductFindDto(product);
    }
}

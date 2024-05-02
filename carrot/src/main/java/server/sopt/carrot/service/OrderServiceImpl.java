package server.sopt.carrot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.dto.OrderCreate;
import server.sopt.carrot.entity.OrderProcessor;
import server.sopt.carrot.entity.Product;
import server.sopt.carrot.error.ErrorMessage;
import server.sopt.carrot.exception.BusinessException;
import server.sopt.carrot.repo.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public OrderCreate.Response createOrder(OrderCreate.Request req,Long customerWhoSellId,Long productId) {
        Product product = productService.findProductById(productId);
        if(product.getCustomer().getId().equals(customerWhoSellId)){
            OrderProcessor orderProcessor = OrderProcessor.of(customerWhoSellId, req.getCustomerWhoBuyId(), productId);
            productService.soldProduct(product);
            orderRepository.save(orderProcessor);
            return OrderCreate.Response.fromEntity(orderProcessor);
        }else{
            throw new BusinessException(ErrorMessage.NOT_MATCH_PRODUCT_CELLER_AND_PRODUCT);
        }
    }
}

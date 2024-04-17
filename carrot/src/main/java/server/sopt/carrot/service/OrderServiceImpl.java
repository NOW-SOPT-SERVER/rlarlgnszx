package server.sopt.carrot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.dto.OrderCreate;
import server.sopt.carrot.entity.OrderProcessor;
import server.sopt.carrot.repo.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public OrderCreate.Response createOrder(OrderCreate.Request req,Long productId) {
        OrderProcessor orderProcessor = OrderProcessor.builder()
                .productId(productId)
                .customerWhoSellId(req.getCustomerWhoSellId())
                .customerWhoBuyId(req.getCustomerWhoBuyId())
                .build();
        productService.soldProduct(productId);
        orderRepository.save(orderProcessor);
        return OrderCreate.Response.fromEntity(orderProcessor);
    }
}

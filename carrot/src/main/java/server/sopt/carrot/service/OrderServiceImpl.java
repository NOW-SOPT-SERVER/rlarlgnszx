package server.sopt.carrot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.dto.OrderCreate;
import server.sopt.carrot.entity.Order;
import server.sopt.carrot.repo.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public OrderCreate.Response createOrder(OrderCreate.Request req) {
        Order order = Order.builder()
                .productId(req.getProductId())
                .customerWhoSellId(req.getCustomerWhoSellId())
                .customerWhoBuyId(req.getCustomerWhoBuyId())
                .build();
        orderRepository.save(order);
        return OrderCreate.Response.fromEntity(order);
    }
}

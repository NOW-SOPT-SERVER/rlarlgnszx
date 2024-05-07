package server.sopt.carrot.service;

import server.sopt.carrot.dto.OrderCreate;

public interface OrderService {
    OrderCreate.Response createOrder(OrderCreate.Request req,Long productId);
}

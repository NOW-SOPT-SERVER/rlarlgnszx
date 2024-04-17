package server.sopt.carrot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.sopt.carrot.dto.OrderCreate;
import server.sopt.carrot.dto.ProductCreate;
import server.sopt.carrot.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public OrderCreate.Response createOrder(
            @Valid @RequestBody OrderCreate.Request req
    ) {
        return orderService.createOrder(req);
    }

}

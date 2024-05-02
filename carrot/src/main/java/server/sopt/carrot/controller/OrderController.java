package server.sopt.carrot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.sopt.carrot.dto.OrderCreate;
import server.sopt.carrot.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("{customerWhoSellId}/{productId}")
    public OrderCreate.Response createOrder(
            @Valid @RequestBody OrderCreate.Request req,
            @PathVariable Long customerWhoSellId,
            @PathVariable Long productId
    ) {
        return orderService.createOrder(req,customerWhoSellId,productId);
    }
}

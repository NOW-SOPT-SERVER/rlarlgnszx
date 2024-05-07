package server.sopt.carrot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.carrot.dto.CustomerCreate;
import server.sopt.carrot.dto.CustomerFindDto;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.service.CustomerService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerCreate.Response createCustomer(
            @Valid @RequestBody CustomerCreate.Request req
    ) {
        return customerService.createCustomer(req);
    }

    @GetMapping("/{customerId}")
    public CustomerFindDto getCustomerBasicInfo(
            @PathVariable Long customerId
    ) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/{customerId}/items")
    public List<ProductFindDto> getCustomerProducts(
            @PathVariable Long customerId
    ) {
        return customerService.getCustomerProducts(customerId);
    }
}

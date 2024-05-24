package server.sopt.carrot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.sopt.carrot.dto.customer.CustomerCreate;
import server.sopt.carrot.dto.customer.CustomerFindDto;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.error.ErrorMessage;
import server.sopt.carrot.exception.BusinessException;
import server.sopt.carrot.mapper.CustomerMapper;
import server.sopt.carrot.service.CustomerService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerMapper customerMapper;
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
        return customerMapper.toCustomerDto(customerService.getCustomerById(customerId));
    }

    @GetMapping("")
    public List<CustomerFindDto> getCustomerBasicInfo(
    ) {
        return customerService.getAllCustomer();
    }
}

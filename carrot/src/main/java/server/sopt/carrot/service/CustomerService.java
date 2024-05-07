package server.sopt.carrot.service;

import server.sopt.carrot.dto.CustomerCreate;
import server.sopt.carrot.dto.CustomerFindDto;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerFindDto getCustomerById(Long customerId);

    CustomerCreate.Response createCustomer(CustomerCreate.Request req);

    List<ProductFindDto> getCustomerProducts(Long customerId);

}

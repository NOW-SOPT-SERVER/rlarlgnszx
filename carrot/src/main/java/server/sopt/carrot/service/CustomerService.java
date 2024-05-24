package server.sopt.carrot.service;

import server.sopt.carrot.dto.customer.CustomerCreate;
import server.sopt.carrot.dto.customer.CustomerFindDto;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerFindDto> getAllCustomer();
    Customer getCustomerById(Long customerId);
    CustomerCreate.Response createCustomer(CustomerCreate.Request req);
//    List<ProductFindDto> getCustomerProducts(Long customerId);
}

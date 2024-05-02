package server.sopt.carrot.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.dto.customer.CustomerCreate;
import server.sopt.carrot.dto.customer.CustomerFindDto;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.error.ErrorMessage;
import server.sopt.carrot.exception.BusinessException;
import server.sopt.carrot.repo.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerCreate.Response createCustomer(CustomerCreate.Request req)
    {
        Customer customer = Customer.builder()
                .name(req.getName())
                .age(req.getAge())
                .build();
        customerRepository.save(customer);
        return CustomerCreate.Response.fromEntity(customer);
    }

    @Override
    public List<CustomerFindDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream().map(CustomerFindDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return findCutomer(customerId);
    }
    private Customer findCutomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CUSTOMER_NOT_FOUND));
    }
//    @Override
//    public List<ProductFindDto> getCustomerProducts(Long customerId) {
//        return productService.getAllProductByCustomerId(customerId);
//    }
}

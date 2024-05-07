package server.sopt.carrot.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.dto.CustomerCreate;
import server.sopt.carrot.dto.CustomerFindDto;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.repo.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductService productService;

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
    public CustomerFindDto getCustomerById(Long customerId) {
        return CustomerFindDto.of(findCutomer(customerId));
    }
    private Customer findCutomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO ID MATCH FOR CUSTOMER"));
    }

    @Override
    public List<ProductFindDto> getCustomerProducts(Long customerId) {
        return productService.getAllProductByCustomerId(customerId);
    }
}

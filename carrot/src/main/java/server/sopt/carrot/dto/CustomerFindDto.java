package server.sopt.carrot.dto;

import server.sopt.carrot.entity.Customer;

public record CustomerFindDto (String name, int age){
    public static CustomerFindDto of(Customer customer){
        return new CustomerFindDto(customer.getName(),customer.getAge());
    }
}

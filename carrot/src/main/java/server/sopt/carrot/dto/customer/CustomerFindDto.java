package server.sopt.carrot.dto.customer;

import jdk.jfr.StackTrace;
import lombok.Getter;
import server.sopt.carrot.entity.Customer;


public record CustomerFindDto (Long id,String name, int age){
    public static CustomerFindDto of(Customer customer){
        return new CustomerFindDto(customer.getId(),customer.getName(),customer.getAge());
    }
}

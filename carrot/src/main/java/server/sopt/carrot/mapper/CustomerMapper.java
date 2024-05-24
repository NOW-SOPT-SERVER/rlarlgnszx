package server.sopt.carrot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.sopt.carrot.dto.customer.CustomerCreate;
import server.sopt.carrot.dto.customer.CustomerFindDto;
import server.sopt.carrot.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
//    @Mapping()
    CustomerFindDto toCustomerDto(Customer customer);
}

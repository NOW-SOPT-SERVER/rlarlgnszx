package server.sopt.carrot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.sopt.carrot.dto.product.ProductFindDto;
import server.sopt.carrot.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //    ProductMapper INSTNACE = Mappers.getMapper(ProductMapper.class)
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "customerId", source = "product.customer.id")
    ProductFindDto toProductFindDto(Product product);
}

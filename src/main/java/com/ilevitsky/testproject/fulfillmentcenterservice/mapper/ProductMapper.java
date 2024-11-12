package com.ilevitsky.testproject.fulfillmentcenterservice.mapper;

import com.ilevitsky.testproject.fulfillmentcenterservice.dto.ProductDto;
import com.ilevitsky.testproject.fulfillmentcenterservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto mapToDto(Product entity);

    Product mapToEntity(ProductDto dto);

    @Mapping(target = "id", ignore = true)
    Product createProductEntityWithoutId(ProductDto productDto);
}

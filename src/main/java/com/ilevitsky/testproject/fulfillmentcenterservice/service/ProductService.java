package com.ilevitsky.testproject.fulfillmentcenterservice.service;

import com.ilevitsky.testproject.fulfillmentcenterservice.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> getAll(String status);
    ProductDto getById(UUID id);
    ProductDto create(ProductDto dto);
    ProductDto update(UUID id, ProductDto dto);
    void delete(UUID id);
}

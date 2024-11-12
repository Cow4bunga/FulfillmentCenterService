package com.ilevitsky.testproject.fulfillmentcenterservice.service.impl;

import com.ilevitsky.testproject.fulfillmentcenterservice.dto.ProductDto;
import com.ilevitsky.testproject.fulfillmentcenterservice.exception.ProductNotFoundException;
import com.ilevitsky.testproject.fulfillmentcenterservice.mapper.ProductMapper;
import com.ilevitsky.testproject.fulfillmentcenterservice.repository.ProductRepository;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll(String status) {
        if (Objects.isNull(status)) {
            return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
        }
        return productRepository.findAll().stream().filter(product -> product.getStatus().getLowercaseName().equals(status)).map(productMapper::mapToDto).toList();
    }

    @Override
    public ProductDto getById(UUID id) {
        return productRepository.findById(id).map(productMapper::mapToDto).orElseThrow(() -> new ProductNotFoundException(String.format("No product with id %s", id)));
    }

    @Override
    public ProductDto create(ProductDto dto) {
        dto.setId(null);
        return mapAndSave(dto);
    }

    @Override
    public ProductDto update(UUID id, ProductDto dto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(String.format("No product with id %s", id));
        }
        var product = productMapper.createProductEntityWithoutId(dto);
        product.setId(id);

        return productMapper.mapToDto(productRepository.save(product));
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    private ProductDto mapAndSave(ProductDto dto) {
        return productMapper.mapToDto(productRepository.save(productMapper.mapToEntity(dto)));
    }
}

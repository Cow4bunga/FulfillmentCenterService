package com.ilevitsky.testproject.fulfillmentcenterservice.service;

import com.ilevitsky.testproject.fulfillmentcenterservice.dto.ProductDto;
import com.ilevitsky.testproject.fulfillmentcenterservice.entity.Product;
import com.ilevitsky.testproject.fulfillmentcenterservice.entity.ProductStatus;
import com.ilevitsky.testproject.fulfillmentcenterservice.exception.ProductNotFoundException;
import com.ilevitsky.testproject.fulfillmentcenterservice.mapper.ProductMapper;
import com.ilevitsky.testproject.fulfillmentcenterservice.repository.ProductRepository;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private static Product product;
    private static ProductDto productDto;

    private static final UUID PROD_ID = UUID.fromString("b9b18de9-971a-488d-95d4-09dfb12d0ec0");

    @BeforeAll
    public static void init() {
        product = Product.builder()
                .id(PROD_ID)
                .productName("Sample Product")
                .status(ProductStatus.SELLABLE)
                .fulfillmentCenter("Warehouse A")
                .quantity(100)
                .value(19.99)
                .build();

        productDto = ProductDto.builder()
                .id(PROD_ID)
                .productName("Sample Product")
                .status("SELLABLE")
                .fulfillmentCenter("Warehouse A")
                .quantity(100)
                .value(19.99)
                .build();
    }

    @Test
    void ProductService_CreateProduct_ReturnsProductDto() {
        when(productMapper.mapToEntity(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.mapToDto(product)).thenReturn(productDto);

        var real = productService.create(productDto);

        assertEquals(productDto, real);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void ProductService_GetAllProducts_ReturnsProductDtoList() {
        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.mapToDto(product)).thenReturn(productDto);

        var real = productService.getAll(null);

        assertEquals(1, real.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void ProductService_GetProductById_ReturnsProductDto() {
        when(productRepository.findById(PROD_ID)).thenReturn(Optional.of(product));
        when(productMapper.mapToDto(product)).thenReturn(productDto);

        var real = productService.getById(PROD_ID);

        assertEquals(productDto, real);
        verify(productRepository, times(1)).findById(PROD_ID);
    }

    @Test
    void ProductService_UpdateProduct_ReturnsProductDto() {
        when(productRepository.existsById(PROD_ID)).thenReturn(true);
        when(productMapper.createProductEntityWithoutId(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.mapToDto(product)).thenReturn(productDto);

        var real = productService.update(PROD_ID, productDto);

        assertEquals(productDto, real);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void ProductService_DeleteProduct_ReturnsNothing() {
        productService.delete(PROD_ID);
        verify(productRepository, times(1)).deleteById(PROD_ID);
    }

    @Test
    void ProductService_GetAllProductsByStatus_ReturnsFilteredProductDtoList() {
        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.mapToDto(product)).thenReturn(productDto);

        var real = productService.getAll("Sellable");

        assertEquals(1, real.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void ProductService_GetProductById_NotFound_ThrowsException() {
        when(productRepository.findById(PROD_ID)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getById(PROD_ID));
        verify(productRepository, times(1)).findById(PROD_ID);
    }

    @Test
    void ProductService_UpdateProduct_NotFound_ThrowsException() {
        when(productRepository.existsById(PROD_ID)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.update(PROD_ID, productDto));
        verify(productRepository, times(1)).existsById(PROD_ID);
    }

    @Test
    void ProductService_GetAllProductsByUnknownStatus_ReturnsEmptyList() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        var real = productService.getAll("UNKNOWN_STATUS");

        assertEquals(0, real.size());
        verify(productRepository, times(1)).findAll();
    }
}
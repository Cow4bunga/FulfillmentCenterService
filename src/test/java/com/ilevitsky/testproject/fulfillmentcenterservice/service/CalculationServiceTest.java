package com.ilevitsky.testproject.fulfillmentcenterservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ilevitsky.testproject.fulfillmentcenterservice.exception.CalculationException;
import com.ilevitsky.testproject.fulfillmentcenterservice.repository.ProductRepository;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.impl.CalculationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void calculateTotalForSellable_ReturnsTotalValue() {
        Double expectedTotal = 100.0;
        when(productRepository.sumValueBySellable()).thenReturn(Optional.of(expectedTotal));

        Double actualTotal = calculationService.calculateTotalForSellable();

        assertEquals(expectedTotal, actualTotal);
        verify(productRepository, times(1)).sumValueBySellable();
    }

    @Test
    void calculateTotalForSellable_ThrowsException_WhenNoValuePresent() {
        when(productRepository.sumValueBySellable()).thenReturn(Optional.empty());

        assertThrows(CalculationException.class, () -> calculationService.calculateTotalForSellable());
        verify(productRepository, times(1)).sumValueBySellable();
    }

    @Test
    void calculateTotalForCenter_ReturnsTotalValue() {
        String center = "Warehouse A";
        Double expectedTotal = 150.0;
        when(productRepository.sumValueByCenter(center)).thenReturn(Optional.of(expectedTotal));

        Double actualTotal = calculationService.calculateTotalForCenter(center);

        assertEquals(expectedTotal, actualTotal);
        verify(productRepository, times(1)).sumValueByCenter(center);
    }

    @Test
    void calculateTotalForCenter_ThrowsException_WhenNoValuePresent() {
        String center = "Warehouse A";
        when(productRepository.sumValueByCenter(center)).thenReturn(Optional.empty());

        assertThrows(CalculationException.class, () -> calculationService.calculateTotalForCenter(center));
        verify(productRepository, times(1)).sumValueByCenter(center);
    }
}

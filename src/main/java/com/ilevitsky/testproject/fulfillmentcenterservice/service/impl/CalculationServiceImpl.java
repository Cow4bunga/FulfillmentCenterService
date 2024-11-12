package com.ilevitsky.testproject.fulfillmentcenterservice.service.impl;

import com.ilevitsky.testproject.fulfillmentcenterservice.exception.CalculationException;
import com.ilevitsky.testproject.fulfillmentcenterservice.repository.ProductRepository;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.CalculationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationsService {
    private final ProductRepository productRepository;

    @Override
    public Double calculateTotalForSellable() {
        return productRepository.sumValueBySellable().orElseThrow(() -> new CalculationException("Cannot calculate sum of sellable products!"));
    }

    @Override
    public Double calculateTotalForCenter(String center) {
        return productRepository.sumValueByCenter(center).orElseThrow(() -> new CalculationException(String.format("Cannot calculate sum of products' prices in center %s!", center)));

    }
}

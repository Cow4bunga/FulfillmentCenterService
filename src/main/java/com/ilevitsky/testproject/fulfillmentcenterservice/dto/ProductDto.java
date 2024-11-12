package com.ilevitsky.testproject.fulfillmentcenterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private UUID id;
    private String productName;
    private String status;
    private String fulfillmentCenter;
    private Integer quantity;
    private Double value;
}

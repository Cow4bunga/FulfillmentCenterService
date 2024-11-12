package com.ilevitsky.testproject.fulfillmentcenterservice.entity;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ProductStatus {
    UNFULFILLABLE(1, "Unfulfillable"),
    SELLABLE(2, "Sellable"),
    INBOUND(3, "Inbound");

    private final long index;
    private final String lowercaseName;

    ProductStatus(long index, String lowercaseName) {
        this.index = index;
        this.lowercaseName = lowercaseName;
    }

    private static final Map<String, ProductStatus> cachedValues;

    static {
        cachedValues = new HashMap<>();
        for (ProductStatus v : ProductStatus.values()) {
            cachedValues.put(v.lowercaseName, v);
        }
    }

    public static ProductStatus getByLowercaseName(String lowercaseName) {
        return cachedValues.get(lowercaseName);
    }

}

package com.ilevitsky.testproject.fulfillmentcenterservice.service;

public interface CalculationsService {
    Double calculateTotalForSellable();

    Double calculateTotalForCenter(String center);
}

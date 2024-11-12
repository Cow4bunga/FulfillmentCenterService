package com.ilevitsky.testproject.fulfillmentcenterservice.repository;

import com.ilevitsky.testproject.fulfillmentcenterservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT SUM(p.value) FROM Product p WHERE p.status = 'SELLABLE'")
    Optional<Double> sumValueBySellable();

    @Query("SELECT SUM(p.value) FROM Product p WHERE p.fulfillmentCenter = :fulfillmentCenter")
    Optional<Double> sumValueByCenter(@Param("fulfillmentCenter") String fulfillmentCenter);
}

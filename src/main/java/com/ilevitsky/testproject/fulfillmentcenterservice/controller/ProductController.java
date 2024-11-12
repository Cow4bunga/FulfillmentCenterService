package com.ilevitsky.testproject.fulfillmentcenterservice.controller;

import com.ilevitsky.testproject.fulfillmentcenterservice.constants.RestPoint;
import com.ilevitsky.testproject.fulfillmentcenterservice.dto.ProductDto;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = RestPoint.PRODUCTS)
@RequiredArgsConstructor
@Tag(name = "Product controller", description = "Controller providing REST API for products service")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(
            summary =
                    "Get all products.",
            description = "Fetches list of all products.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful product retrieval request"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
    })
    public ResponseEntity<List<ProductDto>> getAll(@RequestParam(name = "status", required = false) String productStatus) {
        return new ResponseEntity<>(productService.getAll(productStatus), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary =
                    "Get product by id.",
            description = "Fetches product with corresponding id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved one product by id"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
    })
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") UUID productId) {
        return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary =
                    "Create new product",
            description = "Creates new product. Returns DTO.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created new product"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
    })
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary =
                    "Update existing product by id.",
            description = "Updates existing product state and returns DTO.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated one product by id"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
    })
    public ResponseEntity<ProductDto> updateById(@PathVariable(name = "id") UUID productId, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.update(productId, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary =
                    "Delete product by id.",
            description = "Deletes product with corresponding id. Returns void.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted one product by id"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
    })
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") UUID productId) {
        productService.delete(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
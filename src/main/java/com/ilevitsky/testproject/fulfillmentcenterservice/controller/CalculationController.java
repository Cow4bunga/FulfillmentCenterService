package com.ilevitsky.testproject.fulfillmentcenterservice.controller;

import com.ilevitsky.testproject.fulfillmentcenterservice.constants.RestPoint;
import com.ilevitsky.testproject.fulfillmentcenterservice.service.CalculationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = RestPoint.CALC)
@RequiredArgsConstructor
@Tag(name = "Calculation controller", description = "Controller providing REST API for calculations")
public class CalculationController {
    private final CalculationsService calculationsService;

    @GetMapping("/for-sellable")
    @Operation(
            summary =
                    "Calculate for sellable.",
            description = "Calculates total price for sellable products.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful price calculation"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
            @ApiResponse(responseCode = "409", description = "Calculation error")
    })
    public ResponseEntity<Double> calculateForSellable() {
        return new ResponseEntity<>(calculationsService.calculateTotalForSellable(), HttpStatus.OK);
    }

    @Operation(
            summary =
                    "Calculate for center.",
            description = "Calculates total price for products in concrete center.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful price calculation"),
            @ApiResponse(responseCode = "400", description = "Request failure"),
            @ApiResponse(responseCode = "409", description = "Calculation error")
    })
    @GetMapping("/for-center")
    public ResponseEntity<Double> calculateForSellable(@RequestParam(name = "center") String fulfillmentCenter) {
        return new ResponseEntity<>(calculationsService.calculateTotalForCenter(fulfillmentCenter), HttpStatus.OK);
    }
}

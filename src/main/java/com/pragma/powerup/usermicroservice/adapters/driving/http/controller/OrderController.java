package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListOrderResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderHandler orderHandler;
    @Operation(summary = "Add a new plate",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Order already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PostMapping("create")
    public ResponseEntity<Map<String, String>> savePlate(@RequestBody OrderRequestDto orderRequestDto, @RequestHeader HttpHeaders headers) throws ValidatePlateException, ValidateOrderException {
        String token = headers.getFirst("authorization");
        orderHandler.saveOrder(orderRequestDto, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_CREATED_MESSAGE));
    }

    @SecurityRequirement(name = "jwt")
    @GetMapping("list")
    public ResponseEntity<List<ListOrderResponseDto>> lisPlate ( @RequestHeader HttpHeaders headers,
                                                                  @RequestParam(name = "page") int page,
                                                                @RequestParam(name = "pageSize") int pageSize,
                                                                @RequestParam(name = "status") String status) throws  ValidateOrderException {
        String token = headers.getFirst("authorization");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderHandler.getOrderByIdRestaurant(token, page, pageSize, status));
    }

    @SecurityRequirement(name = "jwt")
    @PostMapping("assing-employee")
    public ResponseEntity<Map<String, String>> assingEmployeeToOrder(@RequestBody List<OrdersToAssing> ordersToAssing,
                                                        @RequestHeader HttpHeaders headers) throws ValidateOrderException {
        String token = headers.getFirst("authorization");
        orderHandler.assingEmployeeToOrder(ordersToAssing, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_ASSING_MESSAGE));
    }
}

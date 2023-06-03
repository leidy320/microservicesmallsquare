package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListRestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
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
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Restaurant already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PostMapping("add")
    public ResponseEntity<Map<String, String>> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto, @RequestHeader HttpHeaders headers) throws ValidateRestaurantException {
       String token = headers.getFirst("authorization");
        restaurantHandler.saveRestaurant(restaurantRequestDto, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.RESTAURANT_CREATED_MESSAGE));
    }

    @SecurityRequirement(name = "jwt")
    @PostMapping("Employe")
    public ResponseEntity<Map<String, String>> addEmployeToRestaurant(@RequestBody EmployeRestaurantRequestDto employeRestaurantRequestDto, @RequestHeader HttpHeaders headers) throws ValidateRestaurantException {
        String token = headers.getFirst("authorization");
        restaurantHandler.addEmployeToRestaurant(employeRestaurantRequestDto, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.EMPLOYE_ADDRESTAURANT_MESSAGE));
    }

    @SecurityRequirement(name = "jwt")
    @GetMapping("list")
    public ResponseEntity<List<ListRestaurantResponseDto>> listRestaurant(@RequestParam(name = "page") int page, @RequestParam(name = "pageSize") int pageSize) throws ValidateRestaurantException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantHandler.getRestaurant(page, pageSize));
    }

}
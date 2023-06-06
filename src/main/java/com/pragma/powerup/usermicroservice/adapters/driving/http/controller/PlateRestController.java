package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListPlateResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListRestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
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
@RequestMapping("/plate/")
@RequiredArgsConstructor

public class PlateRestController {
    private final IPlateHandler plateHandler;

    @Operation(summary = "Add a new plate",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plate created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Plate already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @SecurityRequirement(name = "jwt")
    @PostMapping("create")
    public ResponseEntity<Map<String, String>> savePlate(@RequestBody PlateRequestDto plateRequestDto) throws ValidatePlateException, ValidateCategoryException {
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_CREATED_MESSAGE));
    }
    @SecurityRequirement(name = "jwt")
    @PostMapping("edit")
    public ResponseEntity<Map<String, String>> editPlate(@RequestBody PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException {
        plateHandler.editPlate(plateEditRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_UPDATE_MESSAGE));
    }
    @SecurityRequirement(name = "jwt")
    @PostMapping("enabledisable")
    public ResponseEntity<Map<String, String>> enabledisablePlate(@RequestBody EnableDisablePlateRequestDto enableDisablePlateRequestDto, @RequestHeader HttpHeaders headers) throws ValidatePlateException {
        String token = headers.getFirst("authorization");
        plateHandler.editStatusPlate(enableDisablePlateRequestDto, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_UPDATE_MESSAGE));
    }
    @SecurityRequirement(name = "jwt")
    @GetMapping("list")
    public ResponseEntity<List<ListPlateResponseDto>> lisPlate (@RequestParam(name = "page") int page,
                                                                @RequestParam(name = "pageSize") int pageSize,
                                                                @RequestParam(name = "category") Long idCategory) throws ValidatePlateException, ValidateCategoryException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(plateHandler.getPlate(page, pageSize, idCategory));
    }
}

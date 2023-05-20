package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
    @PostMapping("create")
    public ResponseEntity<Map<String, String>> savePlate(@RequestBody PlateRequestDto plateRequestDto) throws ValidatePlateException {
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PERSON_CREATED_MESSAGE));
    }

    @PostMapping("edit")
    public ResponseEntity<Map<String, String>> editPlate(@RequestBody PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException {
        plateHandler.editPlate(plateEditRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_UPDATE_MESSAGE));
    }

}

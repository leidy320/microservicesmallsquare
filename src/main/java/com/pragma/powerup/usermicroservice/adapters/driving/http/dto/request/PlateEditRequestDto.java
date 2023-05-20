package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlateEditRequestDto {
    private Long id;
    private String description;
    private Double price;
    private String id_owner;

}

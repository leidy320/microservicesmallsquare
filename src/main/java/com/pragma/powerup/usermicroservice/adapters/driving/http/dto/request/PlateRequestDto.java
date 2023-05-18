package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String id_category;
    @NotBlank
    private String description;
    @NotBlank
    private String price;
    @NotBlank
    private String url_image;
    private String active;
    private Long id_restaurant;
    private String id_owner;
}

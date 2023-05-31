package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestaurantRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @NotBlank
    private String nit;
    @NotBlank
    private String idOwner;
    @NotBlank
    private String urlLogo;
}

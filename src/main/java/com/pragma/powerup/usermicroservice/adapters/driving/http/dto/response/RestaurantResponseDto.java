package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantResponseDto {

    private String name;
    private String phone;
    private String address;
    private String nit;
    private String idOwner;
    private String urlLogo;
}

package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmployeRestaurantRequestDto {
    private Long idUser;
    private Long idRestaurant;
    private Long idOwner;
}

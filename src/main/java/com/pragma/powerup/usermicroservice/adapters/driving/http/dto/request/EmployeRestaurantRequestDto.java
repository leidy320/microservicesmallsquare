package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeRestaurantRequestDto {
    private Long idUser;
    private Long idRestaurant;
    private Long idOwner;
}

package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderRequestDto {
    private Long idClient;
    private Long idChef;
    private Long idRestaurant;
    private List<OrderPlateRequestDto> listplates;

}

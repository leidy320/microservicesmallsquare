package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderRequestDto {
    private Long idChef;
    private Long idRestaurant;
    private List<OrderPlateRequestDto> listplates;

}

package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnableDisablePlateRequestDto {
    private Long id;
    private Boolean active;
    private String id_owner;
}

package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderPlateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ListOrderResponseDto {
    private Long idClient;
    private Date date;
    private String status;
    private Long idChef;

}

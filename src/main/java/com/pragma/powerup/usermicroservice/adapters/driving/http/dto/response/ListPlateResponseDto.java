package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListPlateResponseDto {

    private String name;
    private Long id_category;
    private String description;
    private Double price;
    private String url_image;
    private String active;
    private Long id_restaurant;
    private String id_owner;
}

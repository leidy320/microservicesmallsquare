package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlateEntity {
    private String name;
    private String id_category;
    private String description;
    private String price;
    private String url_image;
    private Boolean active;
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;
}

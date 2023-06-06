package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String url_image;
    private Boolean active;
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_category")
    private CategoryEntity category;
}

package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Plate {
    private Long id;
    private String name;
    private String id_category;
    private String description;
    private Double price;
    private String url_image;
    private Boolean active;
    private Long id_restaurant;
    private String id_owner;

    public Plate(Long id, String name, String id_category, String description, Double price, String url_image, Boolean active, Long id_restaurant, String id_owner) {
        this.id = id;
        this.name = name;
        this.id_category = id_category;
        this.description = description;
        this.price = price;
        this.url_image = url_image;
        this.active = active;
        this.id_restaurant = id_restaurant;
        this.id_owner = id_owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Long id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getId_owner() {
        return id_owner;
    }

    public void setId_owner(String id_owner) {
        this.id_owner = id_owner;
    }
}

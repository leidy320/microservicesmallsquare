package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Plate {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String url_image;
    private Boolean active;
    private Restaurant restaurant;
    private Long id_restaurant;
    private Category category;
    private Long id_category;
    private Long id_owner;

    public Plate(Long id, String name, String description, Double price, String url_image, Boolean active,
            Restaurant restaurant, Long id_restaurant, Category category, Long id_category, Long id_owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.url_image = url_image;
        this.active = active;
        this.restaurant = restaurant;
        this.id_restaurant = id_restaurant;
        this.category = category;
        this.id_category = id_category;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Long id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public Long getId_owner() {
        return id_owner;
    }

    public void setId_owner(Long id_owner) {
        this.id_owner = id_owner;
    }
}

package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Plate {
    private Long id;
    private String name;
    private String id_category;
    private String description;
    private String price;
    private String url_image;
    private Boolean active;

    public Plate(Long id, String name, String id_category, String description, String price, String url_image, Boolean active) {
        this.id = id;
        this.name = name;
        this.id_category = id_category;
        this.description = description;
        this.price = price;
        this.url_image = url_image;
        this.active = active;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
}

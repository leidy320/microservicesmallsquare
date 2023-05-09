package com.pragma.powerup.usermicroservice.domain.model;

public class Restaurant {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String nit;
    private String idOwner;
    private String urlLogo;

    public Restaurant(Long id, String name, String phone, String address, String nit, String idOwner, String urlLogo) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.nit = nit;
        this.idOwner = idOwner;
        this.urlLogo = urlLogo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}

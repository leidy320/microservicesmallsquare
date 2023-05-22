package com.pragma.powerup.usermicroservice.domain.apirest;

public interface IUserRestTemplate {
    String getRoleByIdUSer(String id, String token);

    Object getUserBYDni(String dni);
}

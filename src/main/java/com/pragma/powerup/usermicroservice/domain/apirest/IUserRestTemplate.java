package com.pragma.powerup.usermicroservice.domain.apirest;

import org.apache.logging.log4j.message.StringFormattedMessage;

public interface IUserRestTemplate {
    String getRoleByIdUSer(String id, String token);

    String getPhoneById(Long id);
    Object getUserBYDni(String dni);
}

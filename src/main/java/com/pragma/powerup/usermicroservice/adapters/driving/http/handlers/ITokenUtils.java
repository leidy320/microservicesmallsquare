package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import java.util.List;

public interface ITokenUtils {
    String getIdFromToken(String token);
    public List<String> getRoles(String token);
}
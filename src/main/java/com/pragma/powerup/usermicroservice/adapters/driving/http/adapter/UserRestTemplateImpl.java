package com.pragma.powerup.usermicroservice.adapters.driving.http.adapter;

import com.pragma.powerup.usermicroservice.domain.apirest.IUserRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class UserRestTemplateImpl implements IUserRestTemplate {

    RestTemplate restTemplate = new RestTemplate();
    @Value("${microserviceurl.user}")
    String microserviceUserUrl;


    @Override
    public String getRoleByIdUSer(String id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(microserviceUserUrl+"/user/getUser/"+id, HttpMethod.GET,entity, String.class);
        return response.getBody();
    }
}

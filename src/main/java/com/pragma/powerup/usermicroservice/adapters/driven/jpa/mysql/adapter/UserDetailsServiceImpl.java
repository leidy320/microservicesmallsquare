package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl {

    //@Autowired
    //IUserRepository userRepository;

    /*@Override
    public UserDetails loadUserByUsername(String documentID) throws UsernameNotFoundException {
        var usuario = userRepository.findByDniNumber(documentID).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getRole() == null) {
            throw new UsernameNotFoundException("User not found with documentID: " + documentID);
        }

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(usuario.getRole());

        return ;
    }*/
}

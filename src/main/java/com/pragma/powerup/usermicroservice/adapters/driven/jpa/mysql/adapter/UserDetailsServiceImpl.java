package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl /*implements UserDetailsService*/ {

    //@Autowired
    //IUserRepository userRepository;

    /*@Override
    public UserDetails loadUserByUsername(String documentID) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findByDniNumber(documentID).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getRole() == null) {
            throw new UsernameNotFoundException("User not found with documentID: " + documentID);
        }

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(usuario.getRole());

        return PrincipalUser.build(usuario, roles);
    }*/
}

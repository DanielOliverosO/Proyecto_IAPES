package com.proyecto.IAPES.services;

import com.proyecto.IAPES.model.CreacionUsuarios;
import com.proyecto.IAPES.repo.CreacionUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CreacionUsuariosRepository creacionUsuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CreacionUsuarios usuario = creacionUsuariosRepository.findByCorreo(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
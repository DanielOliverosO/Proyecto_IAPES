package com.proyecto.IAPES.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomUserDetails implements UserDetails {

    private Long id; // O Id_User, según prefieras
    private String username; // Correo en tu caso
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Implementaciones de UserDetails (puedes sobrescribirlas si necesitas personalización)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.proyecto.IAPES.repo;

import com.proyecto.IAPES.model.CreacionUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreacionUsuariosRepository extends JpaRepository<CreacionUsuarios, Long> {
    CreacionUsuarios findByCorreo(String correo);
}
package com.proyecto.IAPES.repo;

import com.proyecto.IAPES.model.CreacionUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreacionUsuariosRepository extends JpaRepository<CreacionUsuarios, Long>{
}

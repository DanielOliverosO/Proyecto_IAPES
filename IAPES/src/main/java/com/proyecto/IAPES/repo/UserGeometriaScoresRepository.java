package com.proyecto.IAPES.repo;

import com.proyecto.IAPES.model.GeometriaScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGeometriaScoresRepository extends JpaRepository<GeometriaScores, Long> {

    // Cambia findById_User a findByIdUser
    GeometriaScores findByIdUser(Long idUser);

    // Opcional: buscar por correo a través de la relación
    @Query("SELECT u FROM GeometriaScores u WHERE u.idUser = (SELECT u2.Id_User FROM CreacionUsuarios u2 WHERE u2.correo = :email)")
    GeometriaScores findByUserEmail(String email);
}
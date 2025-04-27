package com.proyecto.IAPES.repo;

import com.proyecto.IAPES.model.GeometriaScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGeometriaScoresRepository extends JpaRepository<GeometriaScores, Long> {

    @Query("SELECT u FROM GeometriaScores u WHERE u.Id_User = (SELECT u2.Id_User FROM CreacionUsuarios u2 WHERE u2.correo = :email)")
    GeometriaScores findByUserEmail(String email);
}
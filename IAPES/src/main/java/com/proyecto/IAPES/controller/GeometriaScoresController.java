package com.proyecto.IAPES.controller;

import com.proyecto.IAPES.model.GeometriaScores;
import com.proyecto.IAPES.repo.UserGeometriaScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/geometria")
public class GeometriaScoresController {

    @Autowired
    private UserGeometriaScoresRepository geometriaScoresRepository;

    @GetMapping
    public GeometriaScores getPercentages(Principal principal) {
        // Depuración: Imprime el estado del Principal
        System.out.println("Principal: " + (principal != null ? principal.getName() : "null"));
        if (principal == null) {
            System.out.println("No authenticated user");
            return new GeometriaScores(0L, 0, 0, 0, 0, 0, 0); // Valores por defecto si no hay usuario autenticado
        }

        String userEmail = principal.getName();
        System.out.println("Email from Principal: " + userEmail);

        // Obtiene los scores usando el repositorio
        GeometriaScores scores = geometriaScoresRepository.findByUserEmail(userEmail);
        System.out.println("Retrieved scores: " + scores);

        // Devuelve los scores encontrados o valores por defecto si no hay datos
        return scores != null ? scores : new GeometriaScores(0L, 0, 0, 0, 0, 0, 0);
    }
}
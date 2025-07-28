package com.proyecto.IAPES.controller;

import com.proyecto.IAPES.model.CreacionUsuarios;
import com.proyecto.IAPES.model.GeometriaScores;
import com.proyecto.IAPES.repo.CreacionUsuariosRepository;
import com.proyecto.IAPES.repo.UserGeometriaScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/geometria")
public class GeometryController {

    private static final Logger logger = LoggerFactory.getLogger(GeometryController.class);

    private final CreacionUsuariosRepository creacionUsuariosRepository;
    private final UserGeometriaScoresRepository geometriaScoresRepository;

    @Autowired
    public GeometryController(CreacionUsuariosRepository creacionUsuariosRepository, UserGeometriaScoresRepository geometriaScoresRepository) {
        this.creacionUsuariosRepository = creacionUsuariosRepository;
        this.geometriaScoresRepository = geometriaScoresRepository;
    }

    @GetMapping
    public String showGeometryPage(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login?error";
        }

        String userEmail = authentication.getName();
        logger.info("Buscando usuario con correo: {}", userEmail);
        CreacionUsuarios user = creacionUsuariosRepository.findByCorreo(userEmail);
        if (user != null) {
            logger.info("Usuario encontrado con Id_User: {}", user.getId_User());
            GeometriaScores scores = geometriaScoresRepository.findByIdUser(user.getId_User());
            Map<String, Integer> areasData = new HashMap<>();
            if (scores != null) {
                logger.info("Datos de GeometriaScores encontrados: ayFpPercentage={}, tpPercentage={}, stPercentage={}, gabPercentage={}, tPercentage={}, vyAsPercentage={}",
                        scores.getAyFpPercentage(), scores.getTpPercentage(), scores.getStPercentage(),
                        scores.getGabPercentage(), scores.getTPercentage(), scores.getVyAsPercentage());
                areasData.put("ayFpPercentage", scores.getAyFpPercentage());
                areasData.put("tpPercentage", scores.getTpPercentage());
                areasData.put("stPercentage", scores.getStPercentage());
                areasData.put("gabPercentage", scores.getGabPercentage());
                areasData.put("tPercentage", scores.getTPercentage());
                areasData.put("vyAsPercentage", scores.getVyAsPercentage());
            } else {
                logger.warn("No se encontraron datos para Id_User: {}, inicializando con ceros", user.getId_User());
                areasData.put("ayFpPercentage", 0);
                areasData.put("tpPercentage", 0);
                areasData.put("stPercentage", 0);
                areasData.put("gabPercentage", 0);
                areasData.put("tPercentage", 0);
                areasData.put("vyAsPercentage", 0);
            }
            model.addAttribute("allAreasData", areasData);
            logger.info("Datos añadidos al modelo: {}", areasData);
        } else {
            logger.error("Usuario no encontrado con correo: {}", userEmail);
            return "redirect:/login?error";
        }

        return "geometria";
    }
}
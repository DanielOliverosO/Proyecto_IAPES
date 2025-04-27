package com.proyecto.IAPES.controller;

import com.proyecto.IAPES.repo.CreacionUsuariosRepository;
import com.proyecto.IAPES.model.CreacionUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

@Controller
@RequestMapping("/register")
public class CreacionUsuariosController {

    private static final Logger logger = LoggerFactory.getLogger(CreacionUsuariosController.class);

    @Autowired
    private CreacionUsuariosRepository creacionUsuariosRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("creacionUsuarios", new CreacionUsuarios());
        return "register"; // Devuelve solo register.html
    }

    @PostMapping
    public String registerUser(@Validated @ModelAttribute("creacionUsuarios") CreacionUsuarios usuario, BindingResult bindingResult, Model model) {
        logger.info("Procesando registro para el usuario: {}", usuario.getCorreo());
        if (bindingResult.hasErrors()) {
            logger.warn("Errores de validación: {}", bindingResult.getAllErrors());
            return "register";
        }

        CreacionUsuarios existingUser = creacionUsuariosRepository.findByCorreo(usuario.getCorreo());
        if (existingUser != null) {
            model.addAttribute("error", "El correo electrónico '" + usuario.getCorreo() + "' ya está registrado.");
            return "register";
        }

        try {
            logger.info("Guardando usuario: {}", usuario);
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            creacionUsuariosRepository.save(usuario);
            logger.info("Usuario registrado exitosamente: {}", usuario.getCorreo());
            return "redirect:/register?success";
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            logger.error("Error al registrar el usuario: {}", errorMessage, e);
            model.addAttribute("error", "Error al registrar el usuario: " + errorMessage);
            return "register";
        } catch (Exception e) {
            logger.error("Error inesperado al registrar el usuario: {}", usuario.getCorreo(), e);
            model.addAttribute("error", "Ocurrió un error inesperado al registrar el usuario");
            return "register";
        }
    }
}
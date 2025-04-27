package com.proyecto.IAPES.controller;

import com.proyecto.IAPES.repo.CreacionUsuariosRepository;
import com.proyecto.IAPES.model.CreacionUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CreacionUsuariosController {

    private static final Logger logger = LoggerFactory.getLogger(CreacionUsuariosController.class);

    @Autowired
    CreacionUsuariosRepository creacionUsuariosRepository;

    @GetMapping("/registrarse")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new CreacionUsuarios());
        return "creacion_usuario";
    }


    @PostMapping("/registrarse")
    public String crear(@Validated CreacionUsuarios usuario,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes attr) {
        logger.info("Procesando registro para el usuario: {}", usuario.getEmail());
        if (bindingResult.hasErrors()) {
            logger.warn("Errores de validación: {}", bindingResult.getAllErrors());
            model.addAttribute("usuario", usuario);
            return "creacion_usuario";
        }
        try {
            logger.info("Guardando usuario: {}", usuario);
            creacionUsuariosRepository.save(usuario);
            logger.info("Usuario registrado exitosamente: {}", usuario.getEmail());
            attr.addFlashAttribute("msgExito", "Usuario creado exitosamente");
            return "redirect:/registrarse";
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            logger.error("Error al registrar el usuario: {}", errorMessage, e);
            model.addAttribute("usuario", usuario);
            if (errorMessage.contains("Correo_Elec")) {
                model.addAttribute("error", "El correo electrónico '" + usuario.getEmail() + "' ya está registrado.");
            } else {
                model.addAttribute("error", "Error al registrar el usuario: " + errorMessage);
            }
            return "creacion_usuario";
        } catch (Exception e) {
            logger.error("Error inesperado al registrar el usuario: {}", usuario.getEmail(), e);
            model.addAttribute("usuario", usuario);
            model.addAttribute("error", "Ocurrió un error inesperado al registrar el usuario: " + e.getMessage());
            return "creacion_usuario";
        }
    }
}

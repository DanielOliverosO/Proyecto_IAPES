package com.proyecto.IAPES.controller;

import com.proyecto.IAPES.model.LoginObject;
import com.proyecto.IAPES.model.CreacionUsuarios;
import com.proyecto.IAPES.repo.CreacionUsuariosRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CreacionUsuariosRepository creacionUsuariosRepository;

    @GetMapping
    public String showLogin(@RequestParam(value = "error", required = false) String error,
                            HttpServletRequest request, Model model) {
        model.addAttribute("loginObject", new LoginObject());

        // Manejo de errores de autenticación
        if (error != null) {
            AuthenticationException authException = (AuthenticationException) request.getSession()
                    .getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            String errorMessage = "Error al iniciar sesión. ";
            if (authException != null) {
                if (authException.getMessage().contains("Bad credentials")) {
                    errorMessage += "Las credenciales (correo o contraseña) son incorrectas.";
                } else if (authException.getMessage().contains("User not found")) {
                    errorMessage += "El usuario no está registrado.";
                } else {
                    errorMessage += "Ocurrió un error desconocido. Contacta al soporte.";
                }
            } else {
                errorMessage += "Por favor, verifica tus credenciales e intenta de nuevo.";
            }
            model.addAttribute("error", errorMessage);
        }

        return "login";
    }

    // Elimina el método @PostMapping, ya que Spring Security lo manejará
}
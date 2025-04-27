package com.proyecto.IAPES.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class CreacionUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_User")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "Nombre")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "Apellido")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Column(name = "Fecha_Na")
    private LocalDate fecha_Na;

    @NotBlank(message = "El género es obligatorio")
    @Column(name = "Genero")
    private String genero;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    @Column(name = "Correo_Elec", unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "Contraseña")
    private String contrasena;
}
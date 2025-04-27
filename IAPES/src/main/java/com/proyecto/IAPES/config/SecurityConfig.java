package com.proyecto.IAPES.config;

import com.proyecto.IAPES.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register", "/", "/css/**", "/js/**", "/webjars/**", "/favicon.ico").permitAll()
                                .requestMatchers("/subjects", "/profile/**").authenticated()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .usernameParameter("correo")
                                .passwordParameter("contrasena")
                                .defaultSuccessUrl("/subjects", true) // Redirige a /subjects tras login exitoso
                                .failureUrl("/login?error") // Redirige con error si falla
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .csrf(csrf ->
                        csrf
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                .ignoringRequestMatchers("/api/**") // Ignora CSRF para APIs si las tienes
                )
                .exceptionHandling(exceptions ->
                        exceptions
                                .accessDeniedPage("/access-denied")
                )
                .userDetailsService(userDetailsService); // Usa el UserDetailsService personalizado

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Costo ajustado a 12 para seguridad en 2025
    }

    // Opcional: Configuración para OAuth2 (descomenta si lo usas)
    /*
    @Bean
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
            )
            .oauth2Login(oauth2 ->
                oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/subjects", true)
                    .failureUrl("/login?error")
            );
        return http.build();
    }
    */
}
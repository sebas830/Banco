package com.banco.banco.config;

import com.banco.banco.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Creamos un Bean para encriptar las contrasenas usando BCrypt
    // Esto sirve para guardar contrasenas seguras en la base de datos
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Este AuthenticationProvider conecta nuestro servicio de usuarios
    // (CustomUserDetailsService) con Spring Security
    // Aqui le decimos como obtener los usuarios y como validar la contrasena
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // usamos nuestro servicio
        authProvider.setPasswordEncoder(passwordEncoder);       // usamos el encoder definido arriba
        return authProvider;
    }

    // Configuracion principal de seguridad web
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Definimos quienes pueden acceder a que rutas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/publico", "/css/**", "/js/**").permitAll() // publico
                        .requestMatchers("/cliente/**").hasRole("CLIENTE") // solo cliente
                        .requestMatchers("/empleado/**").hasRole("EMPLEADO") // solo empleado
                        .requestMatchers("/admin/**").hasRole("ADMIN") // solo admin
                        .anyRequest().authenticated() // todo lo demas requiere login
                )

                // CSRF habilitado (proteccion contra ataques de tipo Cross-Site Request Forgery)
                .csrf(Customizer.withDefaults())

                // Configuraciones de cabeceras de seguridad
                .headers(headers -> headers
                        // Politica de seguridad de contenido (CSP) para scripts, estilos, fuentes, imagenes
                        .contentSecurityPolicy(csp -> csp.policyDirectives(
                                "default-src 'self'; " +
                                        "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; " +
                                        "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://fonts.googleapis.com; " +
                                        "font-src 'self' https://fonts.gstatic.com; " +
                                        "img-src 'self' data:; " +
                                        "object-src 'none'; " +
                                        "frame-ancestors 'none'; " +
                                        "form-action 'self';"
                        ))
                        // HSTS para forzar HTTPS
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .preload(true)
                        )
                        // Evita que otros sitios puedan mostrar nuestra pagina en iframe
                        .frameOptions(frame -> frame.deny())
                )

                // Configuracion del login
                .formLogin(form -> form
                        .loginPage("/login") // pagina de login personalizada
                        // redirige segun el rol despues de iniciar sesion
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().iterator().next().getAuthority();
                            if (role.equals("ROLE_CLIENTE")) {
                                response.sendRedirect("/cliente/dashboard");
                            } else if (role.equals("ROLE_EMPLEADO")) {
                                response.sendRedirect("/empleado/dashboard");
                            } else {
                                response.sendRedirect("/admin/dashboard");
                            }
                        })
                        .permitAll()
                )

                // Configuracion del logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para cerrar sesion
                        .logoutSuccessUrl("/login?logout") // a donde redirige luego de cerrar sesion
                        .invalidateHttpSession(true) // invalida la sesion actual
                        .deleteCookies("JSESSIONID") // elimina cookies de sesion
                        .permitAll()
                )

                // Gestion de sesiones
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login?invalid-session") // si la sesion es invalida
                        .maximumSessions(1)                          // solo 1 sesion por usuario
                        .maxSessionsPreventsLogin(false)             // si se loguea otra vez, expulsa la sesion anterior
                        .expiredUrl("/login?expired")                // si la sesion expira, redirige a login
                );

        // devolvemos la configuracion completa
        return http.build();
    }
}

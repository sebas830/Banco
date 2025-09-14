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

    // Para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationProvider que conecta nuestro servicio de usuarios con Spring Security
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // Configuración de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/publico", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
                        .requestMatchers("/empleado/**").hasRole("EMPLEADO")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // CSRF (habilitado por defecto; lo dejamos explícito)
                .csrf(Customizer.withDefaults())

                // Cabeceras de seguridad modernas (CSP, HSTS, Frame Options)
                .headers(headers -> headers
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
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .preload(true)
                        )
                        .frameOptions(frame -> frame.deny())
                )

                // Login con redirección según rol
                .formLogin(form -> form
                        .loginPage("/login")
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

                // Logout seguro
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                // Gestión de sesiones
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login?invalid-session") // si la sesión es inválida
                        .maximumSessions(1)                          // máximo 1 sesión por usuario
                        .maxSessionsPreventsLogin(false)             // false = expulsa la sesión anterior
                        .expiredUrl("/login?expired")                // si expira sesión -> login
                );

        return http.build();
    }
}

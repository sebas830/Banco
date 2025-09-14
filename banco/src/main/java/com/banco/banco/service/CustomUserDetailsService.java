package com.banco.banco.service;

import com.banco.banco.model.Usuario;
import com.banco.banco.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Repositorio para acceder a los usuarios en la base de datos
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Metodo que Spring Security llama para autenticar un usuario por username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Nos aseguramos que el rol tenga el prefijo ROLE_
        // Spring Security requiere que los roles empiecen con ROLE_
        String rawRol = usuario.getRol() == null ? "" : usuario.getRol().trim();
        String roleName = rawRol.startsWith("ROLE_") ? rawRol : "ROLE_" + rawRol;

        // Creamos la autoridad del usuario
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // Construimos un objeto UserDetails que Spring Security puede usar
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(Collections.singleton(authority)) // le asignamos el rol
                .build();
    }
}

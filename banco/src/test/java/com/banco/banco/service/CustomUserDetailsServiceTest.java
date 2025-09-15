package com.banco.banco.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.banco.banco.model.Usuario;
import com.banco.banco.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

class CustomUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_usuarioExistente() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        when(usuarioRepository.findByUsername("juan")).thenReturn(java.util.Optional.of(usuario));

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("juan");

        // Assert
        assertEquals("juan", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_usuarioNoExiste() {
        when(usuarioRepository.findByUsername("maria")).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class,
                () -> customUserDetailsService.loadUserByUsername("maria"));
    }
}

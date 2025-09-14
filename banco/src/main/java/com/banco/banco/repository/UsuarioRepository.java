package com.banco.banco.repository;

import com.banco.banco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repositorio para manejar las Usuarios en la base de datos
// Extiende JpaRepository que ya trae todos los metodos CRUD (guardar, borrar, buscar, actualizar)
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Metodo para obtener todos los usuario en especifico
    // Spring Data JPA lo interpreta automaticamente y hace la consulta por ti
    Optional<Usuario> findByUsername(String username);
}

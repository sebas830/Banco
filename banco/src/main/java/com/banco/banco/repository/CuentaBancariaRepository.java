package com.banco.banco.repository;

import com.banco.banco.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositorio para manejar las cuentas bancarias en la base de datos
// Extiende JpaRepository que ya trae todos los metodos CRUD (guardar, borrar, buscar, actualizar)
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {

    // Metodo para obtener todas las cuentas de un usuario en especifico
    // Spring Data JPA lo interpreta automaticamente y hace la consulta por ti
    List<CuentaBancaria> findByUsuarioId(Long usuarioId);
}

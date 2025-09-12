package com.banco.banco.repository;

import com.banco.banco.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
    List<CuentaBancaria> findByUsuarioId(Long usuarioId);
}

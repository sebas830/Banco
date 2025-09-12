package com.banco.banco.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Para los roles: CLIENTE, EMPLEADO, ADMIN
    @Column(nullable = false)
    private String rol;

    // Relación con cuentas bancarias
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CuentaBancaria> cuentas = new HashSet<>();

    // Constructor vacío
    public Usuario() {}

    // Constructor con datos
    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Set<CuentaBancaria> getCuentas() { return cuentas; }
    public void setCuentas(Set<CuentaBancaria> cuentas) { this.cuentas = cuentas; }
}
